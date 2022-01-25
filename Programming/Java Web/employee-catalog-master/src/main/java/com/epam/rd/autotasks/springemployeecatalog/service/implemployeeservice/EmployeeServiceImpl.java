package com.epam.rd.autotasks.springemployeecatalog.service.implemployeeservice;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.Paging;
import com.epam.rd.autotasks.springemployeecatalog.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final ServiceFactory factory = new ServiceFactory();

    @Override
    public List<Employee> getAll() {
        return factory.getAllEmployees(HolderQuery.SELECT_QUERY_SQL, false);
    }

    @Override
    public List<Employee> getAllSortBySurname(Paging paging) {
        return factory.employeePaging(
                factory.getAllEmployees(HolderQuery.QUERY_ALL_BY_SURNAME, false),
                paging);
    }

    @Override
    public List<Employee> getAllSortByHireDate(Paging paging) {
        return factory.employeePaging(
                factory.getAllEmployees(HolderQuery.QUERY_ALL_BY_HIRE_DATE, false),
                paging);
    }

    @Override
    public List<Employee> getAllSortByPosition(Paging paging) {
        return factory.employeePaging(
                factory.getAllEmployees(HolderQuery.QUERY_ALL_BY_POSITION, false),
                paging);
    }

    @Override
    public List<Employee> getAllSortBySalary(Paging paging) {
        return factory.employeePaging(
                factory.getAllEmployees(HolderQuery.QUERY_ALL_BY_SALARY, false),
                paging);
    }

    @Override
    public Employee getById(Long employeeId) {
        return factory.getAllEmployees(HolderQuery.SELECT_QUERY_SQL, false).stream()
                .filter(item -> item.getId().equals(employeeId))
                .findAny()
                .orElse(null);
    }

    @Override
    public Employee getByIdWithFullChain(Long employeeId) {
        return factory.getAllEmployeesWithChain(HolderQuery.SELECT_QUERY_SQL, true).stream()
                .filter(item -> item.getId().equals(employeeId))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Employee> getByManagerSortBySurname(Long managerId, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByManager(HolderQuery.QUERY_BY_MANAGER_BY_SURNAME, managerId),
                paging);
    }

    @Override
    public List<Employee> getByManagerSortByHireDate(Long managerId, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByManager(HolderQuery.QUERY_BY_MANAGER_BY_HIRE_DATE, managerId),
                paging);
    }

    @Override
    public List<Employee> getByManagerSortByPosition(Long managerId, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByManager(HolderQuery.QUERY_BY_MANAGER_BY_POSITION, managerId),
                paging);
    }

    @Override
    public List<Employee> getByManagerSortBySalary(Long managerId, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByManager(HolderQuery.QUERY_BY_MANAGER_BY_SALARY, managerId),
                paging);
    }

    @Override
    public List<Employee> getByDepartmentSortBySurname(String department, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByDepartment(HolderQuery.QUERY_BY_DEPARTMENT_BY_SURNAME, department),
                paging);
    }

    @Override
    public List<Employee> getByDepartmentSortByHireDate(String department, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByDepartment(HolderQuery.QUERY_BY_DEPARTMENT_BY_HIRE_DATE, department),
                paging);
    }

    @Override
    public List<Employee> getByDepartmentSortByPosition(String department, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByDepartment(HolderQuery.QUERY_BY_DEPARTMENT_BY_POSITION, department),
                paging);
    }

    @Override
    public List<Employee> getByDepartmentSortBySalary(String department, Paging paging) {
        return factory.employeePaging(
                factory.getEmployeesByDepartment(HolderQuery.QUERY_BY_DEPARTMENT_BY_SALARY, department),
                paging);
    }

    public class HolderQuery {

        private static final String SELECT_QUERY_SQL =
                "SELECT * " +
                        "FROM EMPLOYEE E " +
                        "LEFT JOIN DEPARTMENT D " +
                        "ON E.DEPARTMENT = D.ID ";
        public static final String QUERY_ALL_BY_SURNAME =
                SELECT_QUERY_SQL + "ORDER BY E.LASTNAME";
        public static final String QUERY_ALL_BY_HIRE_DATE =
                SELECT_QUERY_SQL + "ORDER BY E.HIREDATE";
        public static final String QUERY_ALL_BY_POSITION =
                SELECT_QUERY_SQL + "ORDER BY E.POSITION";
        public static final String QUERY_ALL_BY_SALARY =
                SELECT_QUERY_SQL + "ORDER BY E.SALARY";

        private static final String QUERY_BY_MANAGER_BY_SURNAME =
                SELECT_QUERY_SQL + "ORDER BY E.LASTNAME";
        private static final String QUERY_BY_MANAGER_BY_HIRE_DATE =
                SELECT_QUERY_SQL + "ORDER BY E.HIREDATE";
        private static final String QUERY_BY_MANAGER_BY_POSITION =
                SELECT_QUERY_SQL + "ORDER BY E.POSITION";
        private static final String QUERY_BY_MANAGER_BY_SALARY =
                SELECT_QUERY_SQL + "ORDER BY E.SALARY";

        private static final String QUERY_BY_DEPARTMENT_BY_SURNAME =
                SELECT_QUERY_SQL + "ORDER BY E.LASTNAME";
        private static final String QUERY_BY_DEPARTMENT_BY_HIRE_DATE =
                SELECT_QUERY_SQL + "ORDER BY E.HIREDATE";
        private static final String QUERY_BY_DEPARTMENT_BY_POSITION =
                SELECT_QUERY_SQL + "ORDER BY E.POSITION";
        private static final String QUERY_BY_DEPARTMENT_BY_SALARY =
                SELECT_QUERY_SQL + "ORDER BY E.SALARY";

    }

}
