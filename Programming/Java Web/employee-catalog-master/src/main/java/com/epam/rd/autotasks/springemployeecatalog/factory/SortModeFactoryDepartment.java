package com.epam.rd.autotasks.springemployeecatalog.factory;

public class SortModeFactoryDepartment implements ISortModeFactory {

    @Override
    public ISortMode createSortMode() {
        return new SortModeDepartment();
    }

}
