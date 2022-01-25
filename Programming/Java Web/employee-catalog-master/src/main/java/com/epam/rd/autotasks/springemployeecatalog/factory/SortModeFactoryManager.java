package com.epam.rd.autotasks.springemployeecatalog.factory;

public class SortModeFactoryManager implements ISortModeFactory{

    @Override
    public ISortMode createSortMode() {
        return new SortModeManager();
    }

}
