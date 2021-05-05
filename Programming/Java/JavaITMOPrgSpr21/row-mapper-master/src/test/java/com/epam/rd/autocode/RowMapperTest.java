package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class RowMapperTest {

    private static ConnectionSource connectionSource;

    @BeforeClass
    public static void initConnectionSource() {
        connectionSource = ConnectionSource.instance();
    }

    @Test
    public void employeeMapRowSingleTest() throws Exception {
        final RowMapper<Employee> employeeRowMapper = new RowMapperFactory().employeeRowMapper();

        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery("select * from EMPLOYEE where id = '7499'")) {

            rs.next();
            final Employee employee = employeeRowMapper.mapRow(rs);

            assertEquals(
                    new Employee(
                            new BigInteger("7499"),
                            new FullName("JOHN", "ALLEN", "MARIA"),
                            Position.SALESMAN,
                            LocalDate.of(1981, 2, 20),
                            new BigDecimal("1600")
                    ),
                    employee
            );
        }
    }

    @Test
    public void employeeMapRowAllTest() throws Exception {
        final RowMapper<Employee> employeeRowMapper = new RowMapperFactory().employeeRowMapper();

        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery("select * from EMPLOYEE")) {

            final Set<Employee> expected = Files.walk(Paths.get("src/test/resources"))
                    .filter(path -> !Files.isDirectory(path))
                    .filter(file -> file.toString().endsWith(".json"))
                    .map(this::employeeFrom)
                    .collect(Collectors.toSet());

            Set<Employee> actual = new HashSet<>();
            while (rs.next()) {
                actual.add(employeeRowMapper.mapRow(rs));
            }

            assertEquals(
                    expected,
                    actual
            );
        }
    }


    @Test
    public void testNoChangesToReferenceClasses() throws Exception {
        final Path sources = Paths.get("src/test/resources");
        Files.walk(sources)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".ref"))
                .peek(p -> System.out.println(p))
                .forEach(ref -> assertSourceEqualsReference(
                        Paths.get(
                                "src/main/java/com/epam/rd/autocode/domain",
                                ref.getFileName().toString().replace(".ref", ".java")),
                        ref));
    }

    private void assertSourceEqualsReference(final Path src, final Path ref) {
        try {
            final List<String> refLines = Files.readAllLines(src);
            final List<String> srcLines = Files.readAllLines(ref);
            assertEquals(src + " was modified", refLines, srcLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Employee employeeFrom(final Path json) {
        try {
            return Employee.Parser.parseJson(FileUtils.readFileToString(json.toFile(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}