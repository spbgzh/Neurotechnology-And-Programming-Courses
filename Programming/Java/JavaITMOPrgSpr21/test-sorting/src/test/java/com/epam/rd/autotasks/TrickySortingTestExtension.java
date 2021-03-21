package com.epam.rd.autotasks;

public class TrickySortingTestExtension extends SortingTest{
    public TrickySortingTestExtension() {
        sorting = new TrickySortingImpl();
    }
}
