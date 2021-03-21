package com.epam.rd.autotasks.spliterators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangularSpliteratorTest {

    @ParameterizedTest
    @MethodSource("smallIterativeArrays")
    void testSmallIterativeCount(int[][] array) {
        final long expected = array.length * array[0].length;
        final long actual = StreamSupport.intStream(RectangularSpliterator.of(array), false).count();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("smallIterativeArrays")
    void testSmallIterativeSplit(int[][] array) {
        final long expectedCount = array.length * array[0].length;
        Set<Integer> expectedElements = IntStream.iterate(1, i -> i <= expectedCount, i -> i + 1)
                .boxed()
                .collect(toSet());

        final List<RectangularSpliterator> spliterators = recursiveSplit(RectangularSpliterator.of(array));

        assertEquals(expectedCount, spliterators.size());
        for (RectangularSpliterator spliterator : spliterators) {
            assertEquals(1, spliterator.estimateSize());
        }

        Set<Integer> elements = new HashSet<>();
        spliterators.forEach(s -> s.tryAdvance((IntConsumer) elements::add));
        assertEquals(expectedElements, elements);
    }


    @ParameterizedTest
    @MethodSource("smallIterativeArrays")
    void testSmallIterativeSum(int[][] array) {
        final int expected = Arrays.stream(array).flatMapToInt(Arrays::stream).sum();
        final int actual = StreamSupport.intStream(RectangularSpliterator.of(array), false).sum();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("smallIterativeArrays")
    void testSmallIterativeList(int[][] array) {
        final List<Integer> expected = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(toList());
        final List<Integer> actual = StreamSupport.intStream(RectangularSpliterator.of(array), false)
                .boxed()
                .collect(toList());
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("smallRandomArrays")
    void testSmallRandomCount(int[][] array) {
        final long expected = array.length * array[0].length;
        final long actual = StreamSupport.intStream(RectangularSpliterator.of(array), false).count();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("smallRandomArrays")
    void testSmallRandomSplit(int[][] array) {
        final long expectedCount = array.length * array[0].length;
        Set<Integer> expectedElements = Arrays.stream(array).flatMapToInt(Arrays::stream)
                .boxed()
                .collect(toSet());

        final List<RectangularSpliterator> spliterators = recursiveSplit(RectangularSpliterator.of(array));

        assertEquals(expectedCount, spliterators.size());
        for (RectangularSpliterator spliterator : spliterators) {
            assertEquals(1, spliterator.estimateSize());
        }

        Set<Integer> elements = new HashSet<>();
        spliterators.forEach(s -> s.tryAdvance((IntConsumer) elements::add));
        assertEquals(expectedElements, elements);
    }


    @ParameterizedTest
    @MethodSource("smallRandomArrays")
    void testSmallRandomSum(int[][] array) {
        final int expected = Arrays.stream(array).flatMapToInt(Arrays::stream).sum();
        final int actual = StreamSupport.intStream(RectangularSpliterator.of(array), false).sum();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("smallRandomArrays")
    void testSmallRandomList(int[][] array) {
        final List<Integer> expected = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(toList());
        final List<Integer> actual = StreamSupport.intStream(RectangularSpliterator.of(array), false)
                .boxed()
                .collect(toList());
        assertEquals(expected, actual);
    }

    private static List<RectangularSpliterator> recursiveSplit(RectangularSpliterator spliterator) {
        final RectangularSpliterator newSpliterator = spliterator.trySplit();
        if (newSpliterator == null) {
            return List.of(spliterator);
        }
        return Stream.of(recursiveSplit(newSpliterator), recursiveSplit(spliterator))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static Stream<Object[]> smallIterativeArrays() {
        int[][] sizes = new int[][]{
                {1, 1},
                {1, 2},
                {2, 1},
                {2, 2},
                {8, 4},
                {4, 8},
                {10, 10},
                {3, 3},
                {3, 5},
                {7, 4},
                {9, 1},
                {3, 9}
        };

        return Arrays.stream(sizes)
                .map(size -> new int[size[0]][size[1]])
                .peek(RectangularSpliteratorTest::fillIterating)
                .map(arguments -> new Object[]{arguments});
    }

    private static Stream<Object[]> smallRandomArrays() {
        int[][] sizes = new int[][]{
                {1, 1},
                {1, 2},
                {2, 1},
                {2, 2},
                {8, 4},
                {4, 8},
                {10, 10},
                {3, 3},
                {3, 5},
                {7, 4},
                {9, 1},
                {3, 9}
        };

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return Arrays.stream(sizes)
                .map(size -> new int[size[0]][size[1]])
                .peek(array -> fillWithRandom(array, random))
                .map(arguments -> new Object[]{arguments});
    }

    private static void fillIterating(final int[][] array) {
        int c = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = c++;
            }
        }
    }
    private static void fillWithRandom(final int[][] array, Random random) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(10);
            }
        }
    }

    @Test
    void testChainSplit() throws Exception {

        int[][] array = new int[1000][100];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ThreadLocalRandom.current().nextInt(5);
            }
        }

        RectangularSpliterator spliterator = RectangularSpliterator.of(array);

        RectangularSpliterator split = spliterator.trySplit();
        assertEquals(50000, split.estimateSize());
        split = spliterator.trySplit();
        assertEquals(25000, split.estimateSize());
        split = spliterator.trySplit();
        assertEquals(12500, split.estimateSize());

        split.tryAdvance((IntConsumer) i -> {
        });
        assertEquals(12499, split.estimateSize());
        assertEquals(12499, StreamSupport.intStream(split, true).count());
    }

    @Test
    void testParallelSum() throws Exception {

        int[][] array = new int[1000][100];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ThreadLocalRandom.current().nextInt(10);
            }
        }

        int expected = Arrays.stream(array).flatMapToInt(Arrays::stream).sum();
        assertEquals(expected, StreamSupport.intStream(RectangularSpliterator.of(array), false).sum());
        assertEquals(expected, StreamSupport.intStream(RectangularSpliterator.of(array), true).sum());
    }

}