package com.epam.rd.autotasks.springemployeecatalog.factory;

public class SortModeFactoryAll implements ISortModeFactory {

    @Override
    public ISortMode createSortMode() {
        return new SortModeAll();
    }

}
