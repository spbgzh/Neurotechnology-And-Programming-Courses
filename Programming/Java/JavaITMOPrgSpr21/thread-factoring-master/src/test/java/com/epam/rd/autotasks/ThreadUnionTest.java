package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.epam.rd.autotasks.ThrowingConsumer.silentConsumer;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ThreadUnionTest {

    @ParameterizedTest
    @ValueSource(ints = {4, 7, 11})
    void testThreadGenAndCount(int threadsCount) {
        final ThreadUnion threadUnion = ThreadUnion.newInstance(methodName());

        assertEquals(0, threadUnion.totalSize());
        assertEquals(0, threadUnion.activeSize());

        final int longThreadsCount = threadsCount / 2;
        final int shortThreadsCount = threadsCount - longThreadsCount;

        CountDownLatch latch = new CountDownLatch(1);

        List<Thread> longThreads = range(0, longThreadsCount)
                .mapToObj(i -> threadUnion.newThread(() -> awaitLatch(latch)))
                .collect(toList());

        List<Thread> shortThreads = range(0, shortThreadsCount)
                .mapToObj(i -> threadUnion.newThread(this::printThreadName))
                .collect(toList());

        assertEquals(threadsCount, threadUnion.totalSize());
        assertEquals(0, threadUnion.activeSize());

        try {
            longThreads.forEach(Thread::start);
            shortThreads.forEach(Thread::start);
            shortThreads.forEach(silentConsumer(Thread::join));

            assertEquals(threadsCount, threadUnion.totalSize());
            assertEquals(longThreadsCount, threadUnion.activeSize());
        } finally {
            latch.countDown();
            longThreads.forEach(silentConsumer(Thread::join));
        }

        assertEquals(threadsCount, threadUnion.totalSize());
        assertEquals(0, threadUnion.activeSize());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 7, 20})
    void testThreadName(int threadsCount) {
        final String unionName = methodName();
        final ThreadUnion threadUnion = ThreadUnion.newInstance(unionName);

        assertEquals(methodName() + "-worker-0", threadUnion.newThread(this::printThreadName).getName());

        CountDownLatch latch = new CountDownLatch(1);

        final List<CompletableFuture<Thread>> threads;
        try {
            threads = Stream.generate(() -> CompletableFuture.supplyAsync(() -> {
                awaitLatch(latch);
                return threadUnion.newThread(this::printThreadName);
            })).limit(threadsCount).collect(toList());
        } finally {
            latch.countDown();
        }

        final Set<String> threadNames = threads.stream()
                .map(CompletableFuture::join)
                .map(Thread::getName)
                .collect(toSet());

        final Set<String> expectedNames = expectedNames(unionName, 1, threadsCount + 1);

        assertEquals(expectedNames, threadNames);
    }


    @Test
    void testResults() {
        final String unionName = methodName();
        final ThreadUnion threadUnion = ThreadUnion.newInstance(unionName);

        final Set<RuntimeException> exceptionsToThrow = Set.of(
                new RuntimeException("Sample Exception"),
                new IllegalStateException("State Exception"),
                new IllegalArgumentException("Argument Exception"),
                new IndexOutOfBoundsException("Index Exception")
        );
        final int fineThreadsCount = 3;
        final int longThreadsCount = 2;
        final int shortThreadsCount = fineThreadsCount + exceptionsToThrow.size();
        final int allResultsCount = fineThreadsCount + longThreadsCount + exceptionsToThrow.size();

        final List<Thread> exceptionThreads = exceptionsToThrow.stream()
                .map(e -> threadUnion.newThread(() -> throwException(e)))
                .collect(toList());

        final List<Thread> fineThreads = Stream.generate(() -> threadUnion.newThread(this::printThreadName))
                .limit(fineThreadsCount)
                .collect(toList());

        CountDownLatch latch = new CountDownLatch(1);
        final List<Thread> longThreads = Stream.generate(() -> threadUnion.newThread(() -> awaitLatch(latch)))
                .limit(longThreadsCount)
                .collect(toList());

        final LocalDateTime beforeStart = LocalDateTime.now();
        final LocalDateTime afterStart;
        final LocalDateTime afterShortThreadsFinish;
        final LocalDateTime afterLongThreadsFinish;

        final List<FinishedThreadResult> shortResults;

        try {
            startThreads(exceptionThreads, fineThreads, longThreads);
            afterStart = LocalDateTime.now();

            joinThreads(exceptionThreads, fineThreads);
            afterShortThreadsFinish = LocalDateTime.now();

            shortResults = threadUnion.results();
            assertEquals(shortThreadsCount, shortResults.size());
            assertEquals(expectedNames(unionName, 0, shortThreadsCount), resultThreadNames(shortResults));

            shortResults.stream()
                    .map(FinishedThreadResult::getFinished)
                    .forEach(finished -> assertAll(
                            () -> assertFalse(finished.isBefore(beforeStart)),
                            () -> assertFalse(finished.isAfter(afterShortThreadsFinish))
                    ));

            assertEquals(exceptionsToThrow, collectThrowables(shortResults));

        } finally {
            latch.countDown();
            joinThreads(longThreads);
            afterLongThreadsFinish = LocalDateTime.now();
        }

        final List<FinishedThreadResult> allResults = threadUnion.results();

        assertEquals(allResultsCount, allResults.size());
        assertEquals(expectedNames(unionName, 0, allResultsCount), resultThreadNames(allResults));

        final Set<String> shortResultsNames = resultThreadNames(shortResults);
        final Set<FinishedThreadResult> longResults = allResults.stream()
                .filter(r -> !shortResultsNames.contains(r.getThreadName()))
                .collect(toSet());

        longResults.stream()
                .map(FinishedThreadResult::getFinished)
                .forEach(finished -> assertAll(
                        () -> assertFalse(finished.isBefore(beforeStart)),
                        () -> assertFalse(finished.isBefore(afterStart)),
                        () -> assertFalse(finished.isBefore(afterShortThreadsFinish)),
                        () -> assertFalse(finished.isAfter(afterLongThreadsFinish))
                ));

        assertEquals(exceptionsToThrow, collectThrowables(allResults));
    }

    @Test
    void testShutdown() {
        final String unionName = methodName();
        final ThreadUnion threadUnion = ThreadUnion.newInstance(unionName);

        assertFalse(threadUnion.isShutdown());
        assertFalse(threadUnion.isFinished());

        final int threadsCount = 3;

        final List<Thread> threads = Stream.generate(ReentrantLock::new)
                .limit(threadsCount)
                .map(lock -> threadUnion.newThread(() -> {
                    try {
                        final long beforeSleep = System.currentTimeMillis();
                        while (!Thread.currentThread().isInterrupted()) {
                            Thread.sleep(10);
                            if (System.currentTimeMillis() - beforeSleep > 1000 ){
                                throw new NotInterruptedException();
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(toList());

        startThreads(threads);
        assertFalse(threadUnion.isShutdown());
        assertFalse(threadUnion.isFinished());

        threadUnion.shutdown();
        assertTrue(threadUnion.isShutdown());
        assertThrows(IllegalStateException.class, () -> threadUnion.newThread(this::printThreadName));

        threadUnion.awaitTermination();
        assertTrue(threadUnion.isShutdown());
        assertTrue(threadUnion.isFinished());
        assertEquals(threadsCount, threadUnion.totalSize());
        assertEquals(0, threadUnion.activeSize());
        assertEquals(threadsCount, threadUnion.results().size());
        threadUnion.results().forEach(res -> assertFalse(
                res.getThrowable() instanceof NotInterruptedException,
                "Threads were not interrupted"));
        threads.forEach(thread -> assertFalse(thread.isAlive()));
    }

    @SafeVarargs
    private void startThreads(final List<Thread>... threadLists) {
        applyToThreads(Thread::start, threadLists);
    }

    @SafeVarargs
    private void joinThreads(final List<Thread>... threadLists) {
        applyToThreads(silentConsumer(Thread::join), threadLists);
    }

    @SafeVarargs
    private void applyToThreads(final Consumer<Thread> start, final List<Thread>... threadLists) {
        Stream.of(threadLists)
                .flatMap(Collection::stream)
                .forEach(start);
    }

    private Set<Throwable> collectThrowables(final List<FinishedThreadResult> allResults) {
        return allResults.stream()
                .map(FinishedThreadResult::getThrowable)
                .filter(Objects::nonNull)
                .collect(toSet());
    }

    private Set<String> expectedNames(final String unionName, int start, int endExclusive) {
        return IntStream.range(start, endExclusive)
                .mapToObj(i -> unionName + "-worker-" + i)
                .collect(toSet());
    }

    private Set<String> resultThreadNames(final Collection<FinishedThreadResult> shortResults) {
        return shortResults.stream()
                .map(FinishedThreadResult::getThreadName)
                .collect(toSet());
    }

    private void throwException(final RuntimeException e) {
        throw e;
    }

    private String methodName() {
        return StackWalker.getInstance()
                .walk(frames -> frames.skip(1).findFirst()
                        .map(StackWalker.StackFrame::getMethodName)
                        .orElseThrow());
    }

    private void printThreadName() {
        System.out.println(Thread.currentThread().getName());
    }

    private void awaitLatch(final CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}