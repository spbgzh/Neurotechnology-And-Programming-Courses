package com.epam.rd.autocode.decorator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DecoratorsTest {

    @Test
    public void evenElementsSubListTen() {
        final List<String> list = Arrays.asList("1,2,3,4,5,6,7,8,9,10".split(","));
        final List<String> even = Decorators.evenIndexElementsSubList(list);

        assertEquals(5, even.size());
        assertEquals("1", even.get(0));
        assertEquals("3", even.get(1));
        assertEquals("9", even.get(4));
        assertEquals(Arrays.asList("1,3,5,7,9".split(",")), even);
    }

    @Test
    public void evenElementsSubListQwerty() {
        final List<String> list = Arrays.asList("q,w,e,r,t,y,u,i,o,p".split(","));
        final List<String> even = Decorators.evenIndexElementsSubList(list);

        assertEquals(5, even.size());
        assertEquals("q", even.get(0));
        assertEquals("e", even.get(1));
        assertEquals("o", even.get(4));
        assertEquals(Arrays.asList("q,e,t,u,o".split(",")), even);
    }

    @Test
    public void evenElementsSubListAbc() {
        final List<String> list = Arrays.asList("a,b,c,d,e".split(","));
        final List<String> even = Decorators.evenIndexElementsSubList(list);

        assertEquals(3, even.size());
        assertEquals("a", even.get(0));
        assertEquals("c", even.get(1));
        assertEquals("e", even.get(2));
        assertEquals(Arrays.asList("a,c,e".split(",")), even);
    }
}