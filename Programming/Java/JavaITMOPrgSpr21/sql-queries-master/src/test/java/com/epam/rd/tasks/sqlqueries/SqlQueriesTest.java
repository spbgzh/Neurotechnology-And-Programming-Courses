package com.epam.rd.tasks.sqlqueries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SqlQueriesTest {

    private static ConnectionSource connectionSource;
    private SqlQueries sqlQueries;

    @BeforeClass
    public static void initConnectionSource() {
        connectionSource = ConnectionSource.instance();
        ConnectionSource source = connectionSource;
    }

    @Before
    public void initSqlQueries() {
        sqlQueries = new SqlQueries();
    }

    @Test
    public void testSelect01() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select01)) {

            Assert.assertEquals(
                    ImmutableList.of(
                            "7876 - ADAMS - 1100.0",
                            "7499 - ALLEN - 1600.0",
                            "7698 - BLAKE - 2850.0",
                            "7782 - CLARK - 2450.0",
                            "7902 - FORD - 3000.0",
                            "7900 - JAMES - 950.0",
                            "7566 - JONES - 2975.0",
                            "7839 - KING - 5000.0",
                            "7654 - MARTIN - 1250.0",
                            "7934 - MILLER - 1300.0",
                            "7788 - SCOTT - 3000.0",
                            "7369 - SMITH - 800.0",
                            "7844 - TURNER - 1500.0",
                            "7521 - WARD - 1250.0"
                    ),
                    collectResultSetAsIdLastSalaryLines(rs)
            );

        }

    }


    @Test
    public void testSelect02() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select02)) {

            Assert.assertEquals(
                    ImmutableList.of(
                            "7876 - ADAMS - 1100.0",
                            "7499 - ALLEN - 1600.0",
                            "7698 - BLAKE - 2850.0",
                            "7782 - CLARK - 2450.0",
                            "7902 - FORD - 3000.0",
                            "7900 - JAMES - 950.0",
                            "7566 - JONES - 2975.0",
                            "7839 - KING - 5000.0",
                            "7788 - SCOTT - 3000.0",
                            "7369 - SMITH - 800.0",
                            "7521 - WARD - 1250.0"
                    ),
                    collectResultSetAsIdLastSalaryLines(rs)
            );

        }
    }

    @Test
    public void testSelect03() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select03)) {

            Assert.assertEquals(
                    ImmutableSet.of(
                            "7698 - BLAKE - 2850.0",
                            "7782 - CLARK - 2450.0",
                            "7902 - FORD - 3000.0",
                            "7566 - JONES - 2975.0",
                            "7788 - SCOTT - 3000.0"
                    ),
                    new HashSet<>(collectResultSetAsIdLastSalaryLines(rs))
            );

        }
    }

    @Test
    public void testSelect04() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select04)) {

            Assert.assertEquals(
                    ImmutableSet.of(
                            "7876 - ADAMS - 1100.0",
                            "7499 - ALLEN - 1600.0",
                            "7902 - FORD - 3000.0",
                            "7900 - JAMES - 950.0",
                            "7839 - KING - 5000.0",
                            "7654 - MARTIN - 1250.0",
                            "7934 - MILLER - 1300.0",
                            "7788 - SCOTT - 3000.0",
                            "7369 - SMITH - 800.0",
                            "7844 - TURNER - 1500.0",
                            "7521 - WARD - 1250.0"
                    ),
                    new HashSet<>(collectResultSetAsIdLastSalaryLines(rs))
            );

        }
    }

    @Test
    public void testSelect05() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select05)) {

            Assert.assertEquals(
                    ImmutableSet.of(
                            "CLARK - 2450.0 - ACCOUNTING",
                            "MILLER - 1300.0 - ACCOUNTING",
                            "ALLEN - 1600.0 - SALES",
                            "BLAKE - 2850.0 - SALES",
                            "TURNER - 1500.0 - SALES",
                            "SMITH - 800.0 - RESEARCH",
                            "ADAMS - 1100.0 - RESEARCH",
                            "SCOTT - 3000.0 - RESEARCH",
                            "WARD - 1250.0 - SALES",
                            "MARTIN - 1250.0 - SALES",
                            "FORD - 3000.0 - RESEARCH",
                            "JONES - 2975.0 - RESEARCH",
                            "JAMES - 950.0 - SALES"
                    ),
                    new HashSet<>(collectResultSetAsLastSalaryDepLines(rs))
            );

        }
    }

    @Test
    public void testSelect06() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select06)) {

            Assert.assertEquals(
                    ImmutableSet.of(
                            "CLARK - 2450.0 - ACCOUNTING",
                            "MILLER - 1300.0 - ACCOUNTING",
                            "ALLEN - 1600.0 - SALES",
                            "BLAKE - 2850.0 - SALES",
                            "TURNER - 1500.0 - SALES",
                            "SMITH - 800.0 - RESEARCH",
                            "ADAMS - 1100.0 - RESEARCH",
                            "SCOTT - 3000.0 - RESEARCH",
                            "WARD - 1250.0 - SALES",
                            "KING - 5000.0 - null",
                            "MARTIN - 1250.0 - SALES",
                            "FORD - 3000.0 - RESEARCH",
                            "JONES - 2975.0 - RESEARCH",
                            "JAMES - 950.0 - SALES"
                    ),
                    new HashSet<>(collectResultSetAsLastSalaryDepLinesWithDepName(rs))
            );

        }
    }

    @Test
    public void testSelect07() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select07)) {

            rs.next();
            Assert.assertEquals(
                    scale(new BigDecimal(29025)),
                    scale(rs.getBigDecimal("total"))
            );

        }
    }

    @Test
    public void testSelect08() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select08)) {


            Assert.assertEquals(
                    ImmutableSet.of(
                            "ACCOUNTING - 2",
                            "SALES - 6",
                            "RESEARCH - 5"
                    ),
                    new HashSet<>(collectResultSetAsDepToStaffSizeSalary(rs))
            );

        }
    }

    @Test
    public void testSelect09() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select09)) {


            Assert.assertEquals(
                    ImmutableSet.of(
                            "RESEARCH - 10875.00 - 2175.00",
                            "SALES - 9400.00 - 1566.67",
                            "ACCOUNTING - 3750.00 - 1875.00"
                    ),
                    new HashSet<>(collectResultSetAsDepToTotalAndAverageSalary(rs))
            );

        }
    }


    @Test
    public void testSelect10() throws Exception {
        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQueries.select10)) {

            Assert.assertEquals(
                    ImmutableSet.of(
                            "KING - null",
                            "ALLEN - BLAKE",
                            "ADAMS - SCOTT",
                            "SCOTT - JONES",
                            "JONES - KING",
                            "MARTIN - BLAKE",
                            "FORD - JONES",
                            "BLAKE - KING",
                            "SMITH - FORD",
                            "WARD - BLAKE",
                            "TURNER - BLAKE",
                            "JAMES - BLAKE",
                            "CLARK - KING",
                            "MILLER - CLARK"
                    ),
                    new HashSet<>(collectResultSetAsEmployeeNameToManagerName(rs))
            );

        }
    }

    private BigDecimal scale(final BigDecimal n) {
        return n.setScale(2, RoundingMode.HALF_UP);
    }


    private List<String> collectResultSetAsIdLastSalaryLines(final ResultSet rs) throws SQLException {
        List<String> lines = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String last = rs.getString("lastname");
            double salary = rs.getDouble("salary");
            lines.add(id + " - " + last + " - " + salary);
        }
        return lines;
    }

    private List<String> collectResultSetAsLastSalaryDepLines(final ResultSet rs) throws SQLException {
        List<String> lines = new ArrayList<>();
        while (rs.next()) {
            String last = rs.getString("lastname");
            String dep = rs.getString("name");
            double salary = rs.getDouble("salary");
            lines.add(last + " - " + salary + " - " + dep);
        }
        return lines;
    }

    private List<String> collectResultSetAsLastSalaryDepLinesWithDepName(final ResultSet rs) throws SQLException {
        List<String> lines = new ArrayList<>();
        while (rs.next()) {
            String last = rs.getString("lastname");
            String dep = rs.getString("depname");
            double salary = rs.getDouble("salary");
            lines.add(last + " - " + salary + " - " + dep);
        }
        return lines;
    }

    private List<String> collectResultSetAsDepToTotalAndAverageSalary(final ResultSet rs) throws SQLException {
        List<String> lines = new ArrayList<>();
        while (rs.next()) {
            String dep = rs.getString("depname");

            final BigDecimal total = scale(rs.getBigDecimal("total"));
            final BigDecimal average = scale(rs.getBigDecimal("average"));
            lines.add(dep + " - " + total + " - " + average);
        }
        return lines;
    }

    private List<String> collectResultSetAsDepToStaffSizeSalary(final ResultSet rs) throws SQLException {
        List<String> lines = new ArrayList<>();
        while (rs.next()) {
            String dep = rs.getString("depname");

            int staffSize = rs.getInt("staff_size");
            lines.add(dep + " - " + staffSize);
        }
        return lines;
    }

    private List<String> collectResultSetAsEmployeeNameToManagerName(final ResultSet rs) throws SQLException {
        List<String> lines = new ArrayList<>();
        while (rs.next()) {
            String employee = rs.getString("employee");
            String manager = rs.getString("manager");
            lines.add(employee + " - " + manager);
        }
        return lines;
    }


}
