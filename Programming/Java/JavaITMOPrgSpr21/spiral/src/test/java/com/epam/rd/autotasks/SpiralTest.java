package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpiralTest {

    @Test
    void testSpiral34() {

        int[][] expected = new int[][]{
                {1, 2, 3, 4},
                {10, 11, 12, 5},
                {9, 8, 7, 6}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(3, 4)));
    }

    @Test
    void testSpiral43() {
        int[][] expected = new int[][]{
                {1, 2, 3},
                {10, 11, 4},
                {9, 12, 5},
                {8, 7, 6}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(4, 3)));
    }

    @Test
    void testSpiral56() {
        int[][] expected = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {18, 19, 20, 21, 22, 7},
                {17, 28, 29, 30, 23, 8},
                {16, 27, 26, 25, 24, 9},
                {15, 14, 13, 12, 11, 10}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(5, 6)));
    }

    @Test
    void testSpiral55() {
        int[][] expected = new int[][]{
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(5, 5)));
    }

    @Test
    void testSpiral11() {
        int[][] expected = new int[][]{
                {1}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(1, 1)));
    }

    @Test
    void testSpiral12() {
        int[][] expected = new int[][]{
                {1, 2}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(1, 2)));
    }

    @Test
    void testSpiral21() {
        int[][] expected = new int[][]{
                {1},
                {2}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(2, 1)));
    }

    @Test
    void testSpiral22() {
        int[][] expected = new int[][]{
                {1, 2},
                {4, 3}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(2, 2)));
    }

    @Test
    void testSpiral33() {
        int[][] expected = new int[][]{
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        assertEquals(deepToString(expected), deepToString(Spiral.spiral(3, 3)));
    }

    private static String deepToString(int[][] array) {
        final StringJoiner joiner = new StringJoiner(";", "[", "]");
        for (int[] row : array) {
            joiner.add(deepToString(row));
        }
        return joiner.toString();
    }

    private static String deepToString(int[] array) {
        final StringJoiner joiner = new StringJoiner(";", "[", "]");
        for (int value : array) {
            joiner.add(String.valueOf(value));
        }
        return joiner.toString();
    }

}
