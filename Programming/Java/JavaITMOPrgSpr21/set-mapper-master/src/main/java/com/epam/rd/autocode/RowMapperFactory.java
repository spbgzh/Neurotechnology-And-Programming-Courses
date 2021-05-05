package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RowMapperFactory {

    public RowMapper<Employee> employeeWithManagerRowMapper() {
        return new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet) {
                BigInteger id = null;
                FullName fullname;
                Position position = null;
                LocalDate hiredate = null;
                BigDecimal salary = null;
                String first = null;
                String last = null;
                String middle = null;
                Employee manager = null;
                int managerID;

                try {
                    id = BigInteger.valueOf(resultSet.getInt("ID"));

                    first = resultSet.getString("FIRSTNAME");
                    last = resultSet.getString("LASTNAME");
                    middle = resultSet.getString("MIDDLENAME");

                    position = Position.valueOf(resultSet.getString("POSITION"));

                    hiredate = Date.valueOf(String.valueOf(resultSet.getDate("HIREDATE"))).toLocalDate();

                    salary = resultSet.getBigDecimal("SALARY");
                    managerID = resultSet.getInt("MANAGER");
                    resultSet.first();
                    while (resultSet.next()){
                        if(resultSet.getInt("ID")==managerID){
                            manager = new RowMapperFactory().employeeWithManagerRowMapper().mapRow(resultSet);
                        }
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                fullname = new FullName(first,last,middle);

                return new Employee(id, fullname, position, hiredate, salary, manager);
            }
        };
    }
}
