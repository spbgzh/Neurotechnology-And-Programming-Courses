package com.epam.rd.autotasks;

import java.util.Arrays;

public class Sorting {
    public void sort(int[] array) {
        if (array == null)
            throw new UnsupportedOperationException();
        Arrays.sort(array);
    }
}
