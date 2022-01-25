package com.epam.rd.autotasks.springemployeecatalog.factory;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.Paging;

import java.util.List;

public interface ISortMode {

    List<Employee> getSort(String sort, Paging paging, Long managerId, String department);

    List<Employee> getSortBySurname(Paging paging, Long managerId, String department);

    List<Employee> getSortByHireDate(Paging paging, Long managerId, String department);

    List<Employee> getSortByPosition(Paging paging, Long managerId, String department);

    List<Employee> getSortBySalary(Paging paging, Long managerId, String department);

}
