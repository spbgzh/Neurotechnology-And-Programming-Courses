package com.epam.rd.autocode;


import com.epam.rd.autocode.domain.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SetMapperFactory {

    public SetMapper<Set<Employee>> employeesSetMapper() {
        return new SetMapper<Set<Employee>>() {
            @Override
            public Set<Employee> mapSet(ResultSet resultSet) {
                HashSet<Employee> result = new HashSet<>();
                RowMapper<Employee> rowMapper = new RowMapperFactory().employeeWithManagerRowMapper();

                try {
                    resultSet.last();
                    int size = resultSet.getRow();

                    for (int i = 1; i <= size; i++) {
                        resultSet.absolute(i);
                        result.add(rowMapper.mapRow(resultSet));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return result;
            }
        };
    }
}
