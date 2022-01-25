package com.rd.epam.autotasks.scopes;

import com.rd.epam.autotasks.scopes.config.ThreadScopeTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class ThreadScopeTest {

    @Test
    void testThreadScopedBeans() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadScopeTestConfig.class);
        final Object[] beans = Stream.generate(() -> context.getBean("threadBean1"))
                .limit(10)
                .toArray(Object[]::new);

        for (Object bean : beans) {
            assertSame(bean, beans[0]);
        }

        ConcurrentHashMap<Thread, Object> threadToBeans = new ConcurrentHashMap<>();

        final List<Thread> beanThreads = IntStream.range(0, 10)
                .mapToObj(i -> new Thread(
                        () -> threadToBeans.put(
                                Thread.currentThread(),
                                context.getBean("threadBean1"))))
                .collect(toList());

        for (Thread thread : beanThreads) {
            thread.start();
        }
        for (Thread thread : beanThreads) {
            thread.join();
        }

        final Object[] concurrentBeans = threadToBeans.values().toArray();
        for (int i = 0; i < concurrentBeans.length; i++) {
            for (int j = 0; j < concurrentBeans.length; j++) {
                if (i != j) {
                    assertNotSame(concurrentBeans[i], concurrentBeans[j]);
                }
            }
        }
    }

    @Test
    void testDifferentThreadScopedBeans() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadScopeTestConfig.class);
        final Object[] beans1 = Stream.generate(() -> context.getBean("threadBean1"))
                .limit(10)
                .toArray(Object[]::new);
        final Object[] beans2 = Stream.generate(() -> context.getBean("threadBean2"))
                .limit(10)
                .toArray(Object[]::new);

        for (Object bean : beans1) {
            assertSame(bean, beans1[0]);
        }

        for (Object bean : beans2) {
            assertSame(bean, beans2[0]);
        }

        for (final Object bean1 : beans1) {
            for (final Object bean2 : beans2) {
                assertNotSame(bean1, bean2);
            }
        }
    }

}
