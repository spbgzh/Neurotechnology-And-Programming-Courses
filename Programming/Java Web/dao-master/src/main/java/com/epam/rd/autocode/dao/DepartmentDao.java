package com.epam.rd.autocode.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.epam.rd.autocode.domain.Department;

public interface DepartmentDao extends Dao<Department, BigInteger> {
    @Override
    Optional<Department> getById(BigInteger Id);

    @Override
    List<Department> getAll();

    @Override
    Department save(Department department);

    @Override
    void delete(Department department);

}

