package com.epam.rd.autotasks;

import java.util.Random;

public class TrickySortingImpl extends Sorting{
    @Override
    public void sort(final int[] array) {
        super.sort(array);
        array[0] = new Random().nextInt();
    }
}
