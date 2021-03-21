package com.efimchick.ifmo.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.shuffle;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {


    @Test
    public void testPairStringList() {
        List<String> list = new PairStringList();
        assertArrayEquals(new String[]{}, list.toArray(String[]::new));

        list.add("1");
        assertEquals(2, list.size());
        assertEquals("1 1", String.join(" ", list));

        list.add("2");
        list.add("3");
        assertEquals(6, list.size());
        assertEquals("1 1 2 2 3 3", String.join(" ", list));

        list.remove("1");
        assertEquals(4, list.size());
        assertEquals("2 2 3 3", String.join(" ", list));

        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("3 3", String.join(" ", list));

        list.clear();
        assertEquals(0, list.size());
        assertArrayEquals(new String[]{}, list.toArray(String[]::new));

        list.add("a");
        list.add("a");
        list.add("a");
        list.add(0, "b");
        list.add(3, "c");
        list.add(4, "d");
        assertEquals(12, list.size());
        assertEquals("b b a a d d c c a a a a", String.join(" ", list));

        list.set(0, "e");
        list.set(9, "f");
        list.set(6, "g");
        assertEquals(12, list.size());
        assertEquals("e e a a d d g g f f a a", String.join(" ", list));
        assertEquals("d", list.get(4));
        assertEquals("d", list.get(5));

        list.addAll(Arrays.asList("x y z".split(" ")));
        assertEquals("e e a a d d g g f f a a x x y y z z", String.join(" ", list));

        list.addAll(3, Arrays.asList("i j k".split(" ")));
        assertEquals("e e a a i i j j k k d d g g f f a a x x y y z z", String.join(" ", list));

        list.addAll(4, Arrays.asList("m n".split(" ")));
        assertEquals("e e a a m m n n i i j j k k d d g g f f a a x x y y z z", String.join(" ", list));

    }


    @Test
    public void testSortedByAbsoluteValueIntegerSet() {
        Set<Integer> set = new SortedByAbsoluteValueIntegerSet();

        assertEquals(0, set.size());

        Arrays.asList(1, 3, 5, 7, 9).forEach(set::add);
        set.addAll(Arrays.asList(-2, -4, -6, -8, -10));
        assertEquals(10, set.size());
        assertEquals("1 -2 3 -4 5 -6 7 -8 9 -10", toString(set));

        set.remove(-2);
        assertEquals(9, set.size());
        assertEquals("1 3 -4 5 -6 7 -8 9 -10", toString(set));

        assertTrue(set.contains(3));
        set.remove(3);
        assertEquals(8, set.size());
        assertEquals("1 -4 5 -6 7 -8 9 -10", toString(set));
        assertFalse(set.contains(3));


    }

    @Test
    public void testMedianQueue() {

        testCaseMQ(1, "1 0 2", 0, 1, 2);
        testCaseMQ(1, "1 0 2", 0, 2, 1);
        testCaseMQ(1, "1 0 2", 1, 0, 2);
        testCaseMQ(1, "1 0 2", 1, 2, 0);
        testCaseMQ(1, "1 0 2", 2, 0, 1);
        testCaseMQ(1, "1 0 2", 2, 1, 0);

        testCaseMQ(2, "2 1 3 0 4", 0, 1, 2, 3, 4);
        testCaseMQ(0, "0 1", 0, 1);

        testCaseMQ(4, "4 5 3 6 2 7 1 8 0 9", 0, 8, 3, 9, 4, 6, 2, 1, 7, 5);
        testCaseMQ(4, "4 5 3 6 2 7 1 8 0 9", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        testCaseMQ(4, "4 3 5 2 6 1 7 0 8", 0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    private void testCaseMQ(final int expectedMedian, final String expectedQueue, final Integer... elements) {
        Queue<Integer> queue = new MedianQueue();
        assertEquals(0, queue.size());

        stream(elements).forEach(queue::offer);

        assertEquals(Integer.valueOf(expectedMedian), queue.peek());
        assertEquals(elements.length, queue.size());
        assertEquals(Integer.valueOf(expectedMedian), queue.peek());
        assertEquals(expectedQueue, pollQueueToString(queue));
        assertEquals(0, queue.size());

        final ArrayList<Integer> elementsList = new ArrayList<>(asList(elements));
        shuffle(elementsList);
        elementsList.forEach(queue::offer);

        assertEquals("Fail on " + elementsList, Integer.valueOf(expectedMedian), queue.peek());
        assertEquals(elements.length, queue.size());
        assertEquals(Integer.valueOf(expectedMedian), queue.peek());
        assertEquals(expectedQueue, pollQueueToString(queue));
    }

    private String pollQueueToString(final Queue<Integer> queue) {
        return Stream.generate(queue::poll)
                .limit(queue.size())
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }

    private String toString(final Collection<Integer> collection) {
        return String.join(" ", collection.stream()
                .map(i -> Integer.toString(i))
                .toArray(String[]::new));
    }
}