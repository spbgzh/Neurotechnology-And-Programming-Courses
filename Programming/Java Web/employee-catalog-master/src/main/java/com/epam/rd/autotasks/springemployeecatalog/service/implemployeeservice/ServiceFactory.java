package com.epam.rd.autotasks.springemployeecatalog.service.implemployeeservice;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.Paging;
import com.epam.rd.autotasks.springemployeecatalog.exeption.SQLOperationException;
import com.epam.rd.autotasks.springemployeecatalog.service.connectiondb.Connection;
import com.epam.rd.autotasks.springemployeecatalog.service.implmapperlist.MapperFactory;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceFactory {

    private static Statement statement;

    static {
        try {
            statement = Connection.getInstance().createConnectionDb().createStatement();
        } catch (SQLException sqlException) {
            throw new SQLOperationException(sqlException.getMessage(), sqlException);
        }
    }

    public static ResultSet executeQueryVia(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException sqlException) {
            throw new SQLOperationException(sqlException.getMessage(), sqlException);
        }
    }

    public List<Employee> getAllEmployees(String query, Boolean isChain) {
        return new MapperFactory().employeesMapper(isChain).mapping(
                executeQueryVia(query)
        );
    }

    List<Employee> getAllEmployeesWithChain(String query, Boolean isChain) {
        return new MapperFactory().employeesMapper(isChain).mapping(
                executeQueryVia(query)
        );
    }

    List<Employee> getEmployeesByDepartment(String query, String department) {
        String regex = "\\d+";
        if (department.matches(regex)) {
            return getAllEmployees(query, false)
                    .stream()
                    .filter(employee -> employee.getDepartment() != null &&
                            employee.getDepartment().getId().equals(Long.parseLong(department)))
                    .collect(Collectors.toList());
        } else {
            return getAllEmployees(query, false)
                    .stream()
                    .filter(employee -> employee.getDepartment() != null &&
                            employee.getDepartment().getName().equals(department))
                    .collect(Collectors.toList());
        }
    }

    List<Employee> getEmployeesByManager(String query, Long managerId) {
        return getAllEmployees(query, false)
                .stream()
                .filter(employee -> employee.getManager() != null
                        && employee.getManager().getId().equals(managerId))
                .collect(Collectors.toList());
    }

    public List<Employee> employeePaging(List<Employee> employees, Paging paging) {
        List<Employee> employeesPage = new ArrayList<>();
        int indexPaging = paging.page * paging.itemPerPage;
        int numberOfEmployees = employees.size();

        while (indexPaging < numberOfEmployees
                && indexPaging < (paging.page + 1) * paging.itemPerPage) {
            employeesPage.add(employees.get(indexPaging));
            ++indexPaging;
        }
        return employeesPage;
    }

}
