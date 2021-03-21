package com.efimchick.ifmo.collections;

import java.util.*;

public class SortedByAbsoluteValueIntegerSet extends TreeSet<Integer> {

    public SortedByAbsoluteValueIntegerSet(){
        super((o1, o2) -> Integer.compare(Math.abs(o1), Math.abs((o2))));
    }
}
