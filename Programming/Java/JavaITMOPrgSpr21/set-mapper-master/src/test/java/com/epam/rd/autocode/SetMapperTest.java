package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.epam.rd.autocode.domain.Employee.Parser.parseJson;
import static org.junit.Assert.assertEquals;

public class SetMapperTest {

    private static ConnectionSource connectionSource;

    @BeforeClass
    public static void initConnectionSource() {
        connectionSource = ConnectionSource.instance();
    }

    @Test
    public void employeeMapRowSingleTest() throws Exception {
        testSqlQueryWithRelatedEmployeeSet(
                //language=HSQLDB
                "select * from EMPLOYEE where id = '7499'",
                "src/test/resources/one"
        );
    }

    @Test
    public void employeeMapRowAllTest() throws Exception {
        testSqlQueryWithRelatedEmployeeSet(
                //language=HSQLDB
                "select * from EMPLOYEE order by LASTNAME",
                "src/test/resources/all"
        );
    }

    @Test
    public void employeeMapRowOneDepartmentTest() throws Exception {

        testSqlQueryWithRelatedEmployeeSet(
                //language=HSQLDB
                "select * from EMPLOYEE where DEPARTMENT = 30 order by LASTNAME",
                "src/test/resources/sales"
        );
    }

    private void testSqlQueryWithRelatedEmployeeSet(final String s, final String s2) throws SQLException, IOException {
        final SetMapper<Set<Employee>> employeeSetMapper = new SetMapperFactory().employeesSetMapper();

        try (final Connection conn = connectionSource.createConnection();
             final Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             final ResultSet rs = statement.executeQuery(s)) {

            final Set<Employee> expected = Files.walk(Paths.get(s2))
                    .filter(path -> !Files.isDirectory(path))
                    .filter(file -> file.toString().endsWith(".json"))
                    .map(this::employeeFrom)
                    .collect(Collectors.toSet());

            Set<Employee> actual = employeeSetMapper.mapSet(rs);

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
            return parseJson(FileUtils.readFileToString(json.toFile(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}