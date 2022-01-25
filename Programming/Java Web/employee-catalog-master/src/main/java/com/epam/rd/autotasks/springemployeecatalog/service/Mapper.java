package com.epam.rd.autotasks.springemployeecatalog.service;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
@FunctionalInterface
public interface Mapper {

    List<Employee> mapping(ResultSet resultSet);

}
