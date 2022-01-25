package com.epam.rd.autocode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.epam.rd.autocode.dao.DaoFactory;
import com.epam.rd.autocode.dao.DepartmentDao;
import com.epam.rd.autocode.domain.Department;

public class DepartmentDaoTests {

    @Test
    public void departmentDaoFindById() throws Exception {
        final DepartmentDao departmentDao = new DaoFactory().departmentDAO();

        assertFalse(departmentDao.getById(BigInteger.valueOf(55)).isPresent());
        assertTrue(departmentDao.getById(BigInteger.valueOf(30)).isPresent());

        testDep(departmentDao, 10);
        testDep(departmentDao, 20);
        testDep(departmentDao, 30);
        testDep(departmentDao, 40);
    }

    @Test
    public void departmentDaoFindAll() throws Exception {
        final DepartmentDao departmentDao = new DaoFactory().departmentDAO();

        final Set<Department> actual = new HashSet<>(departmentDao.getAll());

        final Set<Department> expected = Files.walk(Paths.get("src/test/resources/dep/"))
                .filter(path -> !Files.isDirectory(path))
                .filter(file -> file.toString().endsWith(".json"))
                .map(this::departmentFrom)
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }

    @Test
    public void departmentDaoSaveDelete() throws Exception {
        final DepartmentDao departmentDao = new DaoFactory().departmentDAO();

        final Set<Department> backup = Files.walk(Paths.get("src/test/resources/dep/"))
                .filter(path -> !Files.isDirectory(path))
                .filter(file -> file.toString().endsWith(".json"))
                .map(this::departmentFrom)
                .collect(Collectors.toSet());

        Set<Department> expected;
        Set<Department> actual;
        {
            expected = backup;
            actual = new HashSet<>(departmentDao.getAll());
            assertEquals(expected, actual);
        }

        {
            final Department oddDep = new Department(new BigInteger("50"), "ODD", "SPB");
            departmentDao.save(oddDep);
            expected = Stream.concat(backup.stream(), Stream.of(oddDep)).collect(Collectors.toSet());
            actual = new HashSet<>(departmentDao.getAll());
            assertEquals(expected, actual);
            assertTrue(departmentDao.getById(new BigInteger("50")).isPresent());
            assertEquals(oddDep, departmentDao.getById(new BigInteger("50")).get());
        }

        {
            final Department oddDep = new Department(new BigInteger("50"), "ODD", "SPB");
            final Department oddDep2 = new Department(new BigInteger("60"), "ODD2", "SPB");
            departmentDao.save(oddDep2);
            expected = Stream.concat(backup.stream(), Stream.of(oddDep, oddDep2)).collect(Collectors.toSet());
            actual = new HashSet<>(departmentDao.getAll());
            assertEquals(expected, actual);
            assertTrue(departmentDao.getById(new BigInteger("60")).isPresent());
            assertEquals(oddDep, departmentDao.getById(new BigInteger("50")).get());
            assertEquals(oddDep2, departmentDao.getById(new BigInteger("60")).get());
        }

        {
            final Department oddDep = new Department(new BigInteger("50"), "ODD", "SPB");
            final Department oddDepMod = new Department(new BigInteger("50"), "ODDMOD", "SPBMOD");
            final Department oddDep2 = new Department(new BigInteger("60"), "ODD2", "SPB");

            assertEquals(oddDep, departmentDao.getById(new BigInteger("50")).get());
            assertEquals(oddDepMod, departmentDao.save(oddDepMod));
            assertEquals(oddDepMod, departmentDao.getById(new BigInteger("50")).get());

            expected = Stream.concat(backup.stream(), Stream.of(oddDepMod, oddDep2)).collect(Collectors.toSet());
            actual = new HashSet<>(departmentDao.getAll());

            assertEquals(expected, actual);
        }

        {
            departmentDao.delete(departmentDao.getById(new BigInteger("50")).get());
            final Department oddDep2 = new Department(new BigInteger("60"), "ODD2", "SPB");
            assertEquals(oddDep2, departmentDao.getById(new BigInteger("60")).get());
            assertFalse(departmentDao.getById(new BigInteger("50")).isPresent());

            expected = Stream.concat(backup.stream(), Stream.of(oddDep2)).collect(Collectors.toSet());
            actual = new HashSet<>(departmentDao.getAll());
            assertEquals(expected, actual);

            departmentDao.delete(departmentDao.getById(new BigInteger("60")).get());
            assertFalse(departmentDao.getById(new BigInteger("60")).isPresent());
        }

        {
            expected = backup;
            actual = new HashSet<>(departmentDao.getAll());
            assertEquals(expected, actual);
        }


    }

    private void testDep(final DepartmentDao departmentDao, final int id) {
        assertEquals(
                departmentFrom(Paths.get("src/test/resources/dep/" + id + ".json")),
                departmentDao.getById(BigInteger.valueOf(id)).get()
        );
    }

    private Department departmentFrom(final Path json) {
        try {
            return Department.Parser.parseJson(FileUtils.readFileToString(json.toFile(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}