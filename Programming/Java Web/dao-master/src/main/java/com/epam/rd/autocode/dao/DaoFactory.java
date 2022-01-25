package com.epam.rd.autocode.dao;

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
import java.util.Objects;
import java.util.Optional;


public class DaoFactory {
    public EmployeeDao employeeDAO() {

        return new EmployeeDao() {
            final List<Employee> employees = new ArrayList<>();
            final ConnectionSource connectionSource= ConnectionSource.instance();

            private void update(Employee employee) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "update EMPLOYEE set FIRSTNAME = ?, LASTNAME = ?, MIDDLENAME = ?, POSITION = ?, MANAGER = ?, HIREDATE = ?, SALARY = ?, DEPARTMENT = ?" +
                                     "where ID = ?")
                ) {
                    ps.setString(1, employee.getFullName().getFirstName());
                    ps.setString(2, employee.getFullName().getLastName());
                    ps.setString(3, employee.getFullName().getMiddleName());
                    ps.setString(4, employee.getPosition().name());
                    ps.setInt(5, Integer.parseInt(String.valueOf(employee.getManagerId())));
                    ps.setString(6, employee.getHired().toString());
                    ps.setBigDecimal(7, employee.getSalary());
                    ps.setInt(8, Integer.parseInt(String.valueOf(employee.getDepartmentId())));
                    ps.setInt(9, Integer.parseInt(String.valueOf(employee.getDepartmentId())));
                    ps.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


            @Override
            public List<Employee> getByDepartment(Department department) {
                List<Employee> employeesByDep = new ArrayList<>();

                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "select ID, FIRSTNAME, LASTNAME, MIDDLENAME, POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT from EMPLOYEE where DEPARTMENT = ?")
                ) {
                    ps.setInt(1, Integer.parseInt(String.valueOf(department.getId())));
                    return getEmployees(employeesByDep, ps);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public List<Employee> getByManager(Employee employee) {
                List<Employee> employeesByMng = new ArrayList<>();

                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "select ID, FIRSTNAME, LASTNAME, MIDDLENAME, POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT from EMPLOYEE where MANAGER = ?")
                ) {

                    ps.setInt(1, employee.getId().intValue());
                    return getEmployees(employeesByMng, ps);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Optional<Employee> getById(BigInteger Id) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "select ID, FIRSTNAME, LASTNAME, MIDDLENAME, POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT from EMPLOYEE where ID = ?")
                ) {
                    ps.setLong(1, Long.parseLong(String.valueOf(Id)));
                    try (final ResultSet resultSet = ps.executeQuery()) {
                        if (resultSet.next()) {

                            return Optional.of(new Employee(
                                    BigInteger.valueOf(resultSet.getInt(1)),
                                    new FullName( resultSet.getString(2),
                                            resultSet.getString(3),
                                            resultSet.getString(4)),
                                    Position.valueOf(resultSet.getString(5)),
                                    LocalDate.parse(resultSet.getString(7)),
                                    new BigDecimal(resultSet.getString(8)!=null?resultSet.getString(8): "0"),
                                    new BigInteger(resultSet.getString(6)!=null?resultSet.getString(6): "0"),
                                    new BigInteger(resultSet.getString(9)!=null?resultSet.getString(9): "0")
                            ));
                        }
                        return Optional.empty();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public List<Employee> getAll() {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "select ID, FIRSTNAME, LASTNAME, MIDDLENAME, POSITION, MANAGER, HIREDATE, SALARY, DEPARTMENT from EMPLOYEE")
                ) {

                    try (final ResultSet resultSet = ps.executeQuery()) {


                        addEmployees(employees, resultSet);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return employees;
            }

            @Override
            public Employee save(Employee employee) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "insert into EMPLOYEE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                ) {


                    if (getById(employee.getId()).isPresent()) {
                        employees.removeIf(x -> Objects.equals(x.getId(), employee.getId()));
                        update(employee);
                    } else {
                        ps.setInt(1, Integer.parseInt(String.valueOf(employee.getId())));
                        ps.setString(2, employee.getFullName().getFirstName());
                        ps.setString(3, employee.getFullName().getLastName());
                        ps.setString(4, employee.getFullName().getMiddleName());
                        ps.setString(5, employee.getPosition().name());
                        ps.setInt(6, Integer.parseInt(String.valueOf(employee.getManagerId())));
                        ps.setString(7, employee.getHired().toString());
                        ps.setBigDecimal(8, employee.getSalary());
                        ps.setInt(9, Integer.parseInt(String.valueOf(employee.getDepartmentId())));
                        ps.executeUpdate();

                    }



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return employee;
            }

            @Override
            public void delete(Employee employee) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "delete from EMPLOYEE where ID = ?")
                ) {

                    employees.removeIf(x -> Objects.equals(x.getId(), employee.getId()));
                    ps.setLong(1,Long.parseLong(String.valueOf(employee.getId())) );

                    ps.executeUpdate();


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        };

    }

    private List<Employee> getEmployees(List<Employee> employeesByMng, PreparedStatement ps) throws SQLException {
        try (final ResultSet resultSet = ps.executeQuery()) {

            addEmployees(employeesByMng, resultSet);

            return employeesByMng;
        }
    }

    private static void addEmployees(List<Employee> employeesByMng, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            employeesByMng.add(new Employee(
                    BigInteger.valueOf(resultSet.getInt(1)),
                    new FullName(
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    ),
                    Position.valueOf(resultSet.getString(5)),
                    LocalDate.parse(resultSet.getString(7)),
                    new BigDecimal(resultSet.getString(8)!=null?resultSet.getString(8): "0"),
                    new BigInteger(resultSet.getString(6)!=null?resultSet.getString(6): "0"),
                    new BigInteger(resultSet.getString(9)!=null?resultSet.getString(9): "0")
            ));
        }
    }

    public DepartmentDao departmentDAO() {

        return new DepartmentDao() {
            final List<Department> departments = new ArrayList<>();
            final ConnectionSource connectionSource= ConnectionSource.instance();


            private void update(Department department) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "update DEPARTMENT set NAME = ?, LOCATION = ? " +
                                     "where ID = ?")
                ) {
                    ps.setString(1, department.getName());
                    ps.setString(2, department.getLocation());
                    ps.setString(3, String.valueOf(department.getId()));
                    ps.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Optional<Department> getById(BigInteger Id) {

                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "select NAME, LOCATION from DEPARTMENT " +
                                     "where ID = ?")
                ) {
                    ps.setLong(1, Long.parseLong(String.valueOf(Id)));
                    try (final ResultSet resultSet = ps.executeQuery()) {
                        if (resultSet.next()) {

                            return Optional.of(new Department(
                                    Id,
                                    resultSet.getString(1),
                                    resultSet.getString(2)
                            ));
                        }
                        return Optional.empty();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public List<Department> getAll() {

                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "select ID, NAME, LOCATION from DEPARTMENT")
                ) {

                    try (final ResultSet resultSet = ps.executeQuery()) {


                        while (resultSet.next()) {



                            departments.add(new Department(
                                    BigInteger.valueOf(resultSet.getInt(1))   ,
                                    resultSet.getString(2),
                                    resultSet.getString(3)
                            ));
                        }

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return departments;
            }

            @Override
            public Department save(Department department) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "insert into DEPARTMENT VALUES ( ?, ?, ?)")
                ) {


                    if (getById(department.getId()).isPresent()) {
                        departments.removeIf(x -> Objects.equals(x.getId(), department.getId()));
                        update(department);
                    } else {
                        ps.setLong(1, Long.parseLong(String.valueOf(department.getId())));
                        ps.setString(2, department.getName());
                        ps.setString(3, department.getLocation());
                        ps.executeUpdate();
                    }



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return department;
            }

            @Override
            public void delete(Department department) {
                try (final Connection connection = connectionSource.createConnection();
                     final PreparedStatement ps = connection.prepareStatement(
                             "delete from DEPARTMENT where ID = ?")
                ) {

                    departments.removeIf(x -> Objects.equals(x.getId(), department.getId()));
                    ps.setLong(1,Long.parseLong(String.valueOf(department.getId())) );

                    ps.executeUpdate();


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };
    }
}
