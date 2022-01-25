package com.epam.rd.autotasks.springemployeecatalog.factory;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.Paging;
import com.epam.rd.autotasks.springemployeecatalog.service.implemployeeservice.EmployeeServiceImpl;

import java.util.List;

public class SortModeDepartment implements ISortMode {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    public List<Employee> getSort(String sort, Paging paging, Long managerId, String department) {
        switch (sort) {
            case "lastName":
                return getSortBySurname(paging, managerId, department);
            case "hired":
                return getSortByHireDate(paging, managerId, department);
            case "position":
                return getSortByPosition(paging, managerId, department);
            case "salary":
                return getSortBySalary(paging, managerId, department);

            default:
                throw new IllegalArgumentException("A non-existent sorting was obtained!");
        }
    }

    @Override
    public List<Employee> getSortBySurname(Paging paging, Long managerId, String department) {
        return employeeService.getByDepartmentSortBySurname(department, paging);
    }

    @Override
    public List<Employee> getSortByHireDate(Paging paging, Long managerId, String department) {
        return employeeService.getByDepartmentSortByHireDate(department, paging);
    }

    @Override
    public List<Employee> getSortByPosition(Paging paging, Long managerId, String department) {
        return employeeService.getByDepartmentSortByPosition(department, paging);
    }

    @Override
    public List<Employee> getSortBySalary(Paging paging, Long managerId, String department) {
        return employeeService.getByDepartmentSortBySalary(department, paging);
    }
}
