package com.epam.rd.autotasks.springemployeecatalog.service.implmapperlist;

import com.epam.rd.autotasks.springemployeecatalog.domain.Department;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.FullName;
import com.epam.rd.autotasks.springemployeecatalog.domain.Position;
import com.epam.rd.autotasks.springemployeecatalog.exeption.SQLOperationException;
import com.epam.rd.autotasks.springemployeecatalog.service.Mapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class MapperFactory {

    void createEmployeeList(ResultSet resultSet, List<Employee> employeeList, Map<Long, Long> managerMap) {
        try {
            while (resultSet.next()) {
                //employee
                Long id = resultSet.getLong("EMPLOYEE.ID");
                String firstName = resultSet.getString("FIRSTNAME");
                String surname = resultSet.getString("LASTNAME");
                String middleName = resultSet.getString("MIDDLENAME");
                Position position = Position.valueOf(resultSet.getString("POSITION"));
                LocalDate hired = resultSet.getDate("HIREDATE").toLocalDate();
                BigDecimal salary = resultSet.getBigDecimal("SALARY");
                long departmentId = resultSet.getLong("DEPARTMENT.ID");
                String name = resultSet.getString("NAME");
                String location = resultSet.getString("LOCATION");
                //manager
                Long managerId = resultSet.getLong("MANAGER");
                managerMap.put(id, managerId);

                employeeList.add(new Employee(
                        id,
                        new FullName(firstName, surname, middleName),
                        position,
                        hired,
                        salary,
                        null,
                        departmentId != 0 ? new Department(departmentId, name, location) : null)
                );
            }
        } catch (SQLException exception) {
            throw new SQLOperationException(exception.getMessage(), exception);
        }
    }

    void createChainEmployees(List<Employee> resultEmployeeList, List<Employee> employeeList, Map<Long, Long> managerMap) {
        for (Employee employee : employeeList) {
            resultEmployeeList.add(new Employee(
                    employee.getId(),
                    employee.getFullName(),
                    employee.getPosition(),
                    employee.getHired(),
                    employee.getSalary(),
                    employeeList.stream()
                            .filter(manager -> managerMap.get(employee.getId()).equals(manager.getId()))
                            .findFirst()
                            .orElse(null),
                    employee.getDepartment()
            ));
        }
    }

    public Mapper employeesMapper(Boolean isChain) {

        if (isChain) {
            return new ImplChainEmployeeMapper();
        } else {
            return new ImplEmployeeMapper();
        }
    }


}
