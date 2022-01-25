package com.epam.rd.autotasks.springemployeecatalog.service;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> getAll();

    List<Employee> getAllSortBySurname(Paging paging);

    List<Employee> getAllSortByHireDate(Paging paging);

    List<Employee> getAllSortByPosition(Paging paging);

    List<Employee> getAllSortBySalary(Paging paging);

    Employee getById(Long employeeId);

    Employee getByIdWithFullChain(Long employeeId);

    List<Employee> getByManagerSortBySurname(Long managerId, Paging paging);

    List<Employee> getByManagerSortByHireDate(Long managerId, Paging paging);

    List<Employee> getByManagerSortBySalary(Long managerId, Paging paging);

    List<Employee> getByManagerSortByPosition(Long managerId, Paging paging);

    List<Employee> getByDepartmentSortBySurname(String department, Paging paging);

    List<Employee> getByDepartmentSortByHireDate(String department, Paging paging);

    List<Employee> getByDepartmentSortByPosition(String department, Paging paging);

    List<Employee> getByDepartmentSortBySalary(String department, Paging paging);

}
