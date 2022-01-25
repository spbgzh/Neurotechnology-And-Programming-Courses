package com.epam.rd.autocode.service;

import java.util.List;

import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;

public interface EmployeeService {

    List<Employee> getAllSortByHireDate(Paging paging);

    List<Employee> getAllSortByLastname(Paging paging);

    List<Employee> getAllSortBySalary(Paging paging);

    List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging);

    List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging);

    List<Employee> getByDepartmentSortBySalary(Department department, Paging paging);

    List<Employee> getByDepartmentSortByLastname(Department department, Paging paging);

    List<Employee> getByManagerSortByLastname(Employee manager, Paging paging);

    List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging);

    List<Employee> getByManagerSortBySalary(Employee manager, Paging paging);

    Employee getWithDepartmentAndFullManagerChain(Employee employee);

    Employee getTopNthBySalaryByDepartment(int salaryRank, Department department);
}
