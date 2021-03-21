package com.epam.rd.autotasks;

public class LazySortingTestExtension extends SortingTest {

    public LazySortingTestExtension() {
        sorting = new LazySortingImpl();

    }
}
