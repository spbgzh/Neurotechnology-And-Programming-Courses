package com.epam.rd.autocode.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;

public interface EmployeeDao extends Dao<Employee, BigInteger> {
    List<Employee> getByDepartment(Department department);
    List<Employee> getByManager(Employee employee);
    @Override
    Optional<Employee> getById(BigInteger Id);

    @Override
    List<Employee> getAll();

    @Override
    Employee save(Employee employee);

    @Override
    void delete(Employee employee);
}

