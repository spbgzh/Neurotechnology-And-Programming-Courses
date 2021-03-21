package com.epam.rd.autotasks;

public class NullCarelessSortingTestExtension extends SortingTest{

    public NullCarelessSortingTestExtension() {
        sorting = new NullCarelessSortingImpl();
    }
}
