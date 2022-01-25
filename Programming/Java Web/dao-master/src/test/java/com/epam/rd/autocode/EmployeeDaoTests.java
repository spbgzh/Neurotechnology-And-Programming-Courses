package com.epam.rd.autocode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.epam.rd.autocode.dao.EmployeeDao;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.epam.rd.autocode.dao.DaoFactory;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

public class EmployeeDaoTests {

    @Test
    public void employeeDaoFindById() throws Exception {
        final EmployeeDao employeeDao = new DaoFactory().employeeDAO();

        assertFalse(employeeDao.getById(BigInteger.valueOf(55)).isPresent());
        assertTrue(employeeDao.getById(BigInteger.valueOf(7521)).isPresent());

        testEmp(employeeDao, 7839);
        testEmp(employeeDao, 7698);
        testEmp(employeeDao, 7844);
        testEmp(employeeDao, 7900);
    }

    @Test
    public void employeeDaoFindAll() throws Exception {
        final EmployeeDao employeeDao = new DaoFactory().employeeDAO();

        final Set<Employee> actual = new HashSet<>(employeeDao.getAll());

        final Set<Employee> expected = Files.walk(Paths.get("src/test/resources/emp/"))
                .filter(path -> !Files.isDirectory(path))
                .filter(file -> file.toString().endsWith(".json"))
                .map(this::employeeFrom)
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }

    @Test
    public void employeeDaoFindByDep() throws Exception {
        final EmployeeDao employeeDao = new DaoFactory().employeeDAO();

        testEmpFromDep(employeeDao, new Department(new BigInteger("10"), "ACCOUNTING", "NEW YORK"));
        testEmpFromDep(employeeDao, new Department(new BigInteger("20"), "RESEARCH", "DALLAS"));
        testEmpFromDep(employeeDao, new Department(new BigInteger("30"), "SALES", "CHICAGO"));
        testEmpFromDep(employeeDao, new Department(new BigInteger("40"), "OPERATIONS", "BOSTON"));
    }

    @Test
    public void employeeDaoFindByMgr() throws Exception {
        final EmployeeDao employeeDao = new DaoFactory().employeeDAO();

        testEmpByMgr(employeeDao, new BigInteger("7839"));
        testEmpByMgr(employeeDao, new BigInteger("7369"));
        testEmpByMgr(employeeDao, new BigInteger("7499"));
        testEmpByMgr(employeeDao, new BigInteger("7566"));
        testEmpByMgr(employeeDao, new BigInteger("7844"));
        testEmpByMgr(employeeDao, new BigInteger("7900"));
        testEmpByMgr(employeeDao, new BigInteger("7934"));
    }

    private void testEmpFromDep(final EmployeeDao employeeDao, final Department dep) throws IOException {
        final Set<Employee> actual = new HashSet<>(employeeDao.getByDepartment(dep));

        final Set<Employee> expected = Files.walk(Paths.get("src/test/resources/emp/"))
                .filter(path -> !Files.isDirectory(path))
                .filter(file -> file.toString().endsWith(".json"))
                .map(this::employeeFrom)
                .filter(e -> e.getDepartmentId().equals(dep.getId()))
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }

    private void testEmpByMgr(final EmployeeDao employeeDao, final BigInteger mgrId) throws IOException {
        final Set<Employee> actual = new HashSet<>(employeeDao.getByManager(employeeDao.getById(mgrId).get()));

        final Set<Employee> expected = Files.walk(Paths.get("src/test/resources/emp/"))
                .filter(path -> !Files.isDirectory(path))
                .filter(file -> file.toString().endsWith(".json"))
                .map(this::employeeFrom)
                .filter(e -> e.getManagerId().equals(mgrId))
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }

    @Test
    public void departmentDaoSaveDelete() throws Exception {
        final EmployeeDao employeeDao = new DaoFactory().employeeDAO();

        final Set<Employee> backup = Files.walk(Paths.get("src/test/resources/emp/"))
                .filter(path -> !Files.isDirectory(path))
                .filter(file -> file.toString().endsWith(".json"))
                .map(this::employeeFrom)
                .collect(Collectors.toSet());

        Set<Employee> expected;
        Set<Employee> actual;
        {
            expected = backup;
            actual = new HashSet<>(employeeDao.getAll());
            assertEquals(expected, actual);
        }

        {
            final Employee oddEmp = new Employee(
                    new BigInteger("8000"),
                    new FullName("first", "last", "middle"),
                    Position.CLERK,
                    LocalDate.now(),
                    new BigDecimal("1324"),
                    new BigInteger("7788"),
                    new BigInteger("20"));

            assertEquals(oddEmp, employeeDao.save(oddEmp));

            expected = Stream.concat(backup.stream(), Stream.of(oddEmp)).collect(Collectors.toSet());
            actual = new HashSet<>(employeeDao.getAll());

            assertEquals(expected, actual);
            assertTrue(employeeDao.getById(new BigInteger("8000")).isPresent());
            assertEquals(oddEmp, employeeDao.getById(new BigInteger("8000")).get());
        }

        {
            employeeDao.delete(employeeDao.getById(new BigInteger("8000")).get());
            assertFalse(employeeDao.getById(new BigInteger("8000")).isPresent());
        }

        {
            expected = backup;
            actual = new HashSet<>(employeeDao.getAll());
            assertEquals(expected, actual);
        }


    }


    private void testEmp(final EmployeeDao employeeDao, final int id) {
        assertEquals(
                employeeFrom(Paths.get("src/test/resources/emp/" + id + ".json")),
                employeeDao.getById(BigInteger.valueOf(id)).get()
        );
    }

    private Employee employeeFrom(final Path json) {
        try {
            return Employee.Parser.parseJson(FileUtils.readFileToString(json.toFile(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}