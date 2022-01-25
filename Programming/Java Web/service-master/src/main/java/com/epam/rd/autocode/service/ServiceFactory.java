package com.epam.rd.autocode.service;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceFactory implements EmployeeService {

    final ConnectionSource connectionSource;

    public ServiceFactory() {
        connectionSource = ConnectionSource.instance();
    }

    public EmployeeService employeeService() {
        return new ServiceFactory();
    }

    @Override
    public List<Employee> getAllSortByHireDate(Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE order by HIREDATE")) {
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getAllSortByLastname(Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE order by LASTNAME")) {
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getAllSortBySalary(Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE order by SALARY")) {
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE order by DEPARTMENT, LASTNAME")) {
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where DEPARTMENT = ? order by HIREDATE")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(department.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getByDepartmentSortBySalary(Department department, Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where DEPARTMENT = ? order by SALARY")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(department.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getByDepartmentSortByLastname(Department department, Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where DEPARTMENT = ? order by LASTNAME")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(department.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getByManagerSortByLastname(Employee manager, Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where MANAGER = ? order by LASTNAME")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(manager.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where MANAGER = ? order by HIREDATE")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(manager.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public List<Employee> getByManagerSortBySalary(Employee manager, Paging paging) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where MANAGER = ? order by SALARY")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(manager.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GetListFromPage(employeeList, paging);
    }

    @Override
    public Employee getWithDepartmentAndFullManagerChain(Employee employee) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where ID = ?")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(employee.getId())));
            employeeList = getEmployeesWithFullChain(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList.get(0);
    }

    @Override
    public Employee getTopNthBySalaryByDepartment(int salaryRank, Department department) {
        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE where DEPARTMENT = ? order by SALARY DESC")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(department.getId())));
            employeeList = getEmployeesWithOneManager(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList.get(salaryRank - 1);
    }

    private List<Employee> getEmployeesWithOneManager(PreparedStatement ps) {
        List<Employee> employeeList = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                employeeList.add(new Employee(
                        BigInteger.valueOf(rs.getLong(1)),
                        new FullName(
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)
                        ),
                        Position.valueOf(rs.getString(5)),
                        LocalDate.parse(rs.getString(7)),
                        BigDecimal.valueOf(rs.getDouble(8)),
                        getManager(BigInteger.valueOf(rs.getLong(6))),
                        getDepartment(BigInteger.valueOf(rs.getLong(9)))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private List<Employee> getEmployeesWithFullChain(PreparedStatement ps) {
        List<Employee> employeeList = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                employeeList.add(new Employee(
                        BigInteger.valueOf(rs.getLong(1)),
                        new FullName(
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)
                        ),
                        Position.valueOf(rs.getString(5)),
                        LocalDate.parse(rs.getString(7)),
                        BigDecimal.valueOf(rs.getDouble(8)),
                        getFullChain(BigInteger.valueOf(rs.getLong(6))),
                        getDepartment(BigInteger.valueOf(rs.getLong(9)))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private Employee getManager(BigInteger manager_id) {

        Employee manager = null;

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement ps = connection.prepareStatement("select * from EMPLOYEE")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (manager_id == null) {
                        break;
                    }
                    if (manager_id.equals(BigInteger.valueOf(rs.getLong(1)))) {
                        manager = new Employee(
                                BigInteger.valueOf(rs.getLong(1)),
                                new FullName(
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4)
                                ),
                                Position.valueOf(rs.getString(5)),
                                LocalDate.parse(rs.getString(7)),
                                BigDecimal.valueOf(rs.getDouble(8)),
                                null,
                                getDepartment(BigInteger.valueOf(rs.getLong(9)))
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    private Employee getFullChain(BigInteger manager_id) {

        Employee manager = null;

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement ps = connection.prepareStatement("select * from EMPLOYEE")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (manager_id.equals(BigInteger.valueOf(rs.getLong(1)))) {
                        manager = new Employee(
                                BigInteger.valueOf(rs.getLong(1)),
                                new FullName(
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4)
                                ),
                                Position.valueOf(rs.getString(5)),
                                LocalDate.parse(rs.getString(7)),
                                BigDecimal.valueOf(rs.getDouble(8)),
                                getFullChain(BigInteger.valueOf(rs.getLong(6))),
                                getDepartment(BigInteger.valueOf(rs.getLong(9)))
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    private Department getDepartment(BigInteger id) {

        Department department = null;

        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT NAME, LOCATION FROM DEPARTMENT WHERE ID = ?")) {
            preparedStatement.setLong(1, Long.parseLong(String.valueOf(id)));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    department = new Department(
                            id,
                            rs.getString(1),
                            rs.getString(2)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    private List<Employee> GetListFromPage(List<Employee> employeeList, Paging paging) {
        List<List<Employee>> Pages = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i += paging.itemPerPage) {
            List<Employee> Page = new ArrayList<>(employeeList.subList(i, Math.min(employeeList.size(), i + paging.itemPerPage)));
            Pages.add(Page);
        }
        return Pages.get(paging.page - 1);
    }

}
