package com.epam.rd.autocode;

import static com.epam.rd.autocode.service.Paging.page;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.service.EmployeeService;
import com.epam.rd.autocode.service.Paging;
import com.epam.rd.autocode.service.ServiceFactory;

public class EmployeeServiceTests {

    private EmployeeService employeeService;


    @Before
    public void setUp() throws Exception {
        employeeService = new ServiceFactory().employeeService();
    }

    //ALL

    @Test
    public void testAllSortByHiredDate() throws IOException {

        final String filter = "all";
        final String sort = "hired";

        final List<Paging> pagings = getPagings(filter, sort);

        for (Paging paging : pagings) {
            final List<Employee> actual = employeeService.getAllSortByHireDate(paging);
            final List<Employee> expected = readEmployees(
                    Paths.get("src/test/resources", filter, sort, dirNameFromPaging(paging))
            );
            assertThat(actual, containsInAnyOrder(expected.toArray()));
            assertThat(expected, containsInAnyOrder(actual.toArray()));
        }
    }

    @Test
    public void testAllSortByLastname() throws IOException {

        final String filter = "all";
        final String sort = "lastname";

        final List<Paging> pagings = getPagings(filter, sort);

        for (Paging paging : pagings) {
            final List<Employee> actual = employeeService.getAllSortByLastname(paging);
            final List<Employee> expected = readEmployees(
                    Paths.get("src/test/resources", filter, sort, dirNameFromPaging(paging))
            );
            assertThat(actual, containsInAnyOrder(expected.toArray()));
            assertThat(expected, containsInAnyOrder(actual.toArray()));
        }
    }

    @Test
    public void testAllSortBySalary() throws IOException {

        final String filter = "all";
        final String sort = "salary";

        final List<Paging> pagings = getPagings(filter, sort);

        for (Paging paging : pagings) {
            final List<Employee> actual = employeeService.getAllSortBySalary(paging);
            final List<Employee> expected = readEmployees(
                    Paths.get("src/test/resources", filter, sort, dirNameFromPaging(paging))
            );
            assertThat(actual, containsInAnyOrder(expected.toArray()));
            assertThat(expected, containsInAnyOrder(actual.toArray()));
        }
    }

    @Test
    public void testAllSortByDepartmentNameAndLastname() throws IOException {

        final String filter = "all";
        final String sort = "deplast";

        final List<Paging> pagings = getPagings(filter, sort);

        for (Paging paging : pagings) {
            final List<Employee> actual = employeeService.getAllSortByDepartmentNameAndLastname(paging);
            final List<Employee> expected = readEmployees(
                    Paths.get("src/test/resources", filter, sort, dirNameFromPaging(paging))
            );
            assertThat(actual, containsInAnyOrder(expected.toArray()));
            assertThat(expected, containsInAnyOrder(actual.toArray()));
        }
    }

    //By Dep

    @Test
    public void testByDepartmentSortBySalary() throws IOException {

        final String filter = "by_dep";
        final String sort = "salary";


        for (Department dep : allDeps()) {
            final List<Paging> pagings = getPagings(filter, sort, String.valueOf(dep.getId()));
            for (Paging paging : pagings) {
                final List<Employee> actual = employeeService.getByDepartmentSortBySalary(dep, paging);
                final List<Employee> expected = readEmployees(
                        Paths.get("src/test/resources", filter, sort, String.valueOf(dep.getId()), dirNameFromPaging(paging))
                );
                assertThat(actual, containsInAnyOrder(expected.toArray()));
                assertThat(expected, containsInAnyOrder(actual.toArray()));
            }
        }
    }

    @Test
    public void testByDepartmentSortByHireDate() throws IOException {

        final String filter = "by_dep";
        final String sort = "hired";


        for (Department dep : allDeps()) {
            final List<Paging> pagings = getPagings(filter, sort, String.valueOf(dep.getId()));
            for (Paging paging : pagings) {
                final List<Employee> actual = employeeService.getByDepartmentSortByHireDate(dep, paging);
                final List<Employee> expected = readEmployees(
                        Paths.get("src/test/resources", filter, sort, String.valueOf(dep.getId()), dirNameFromPaging(paging))
                );
                assertThat(actual, containsInAnyOrder(expected.toArray()));
                assertThat(expected, containsInAnyOrder(actual.toArray()));
            }
        }
    }

    @Test
    public void testByDepartmentSortByLastname() throws IOException {

        final String filter = "by_dep";
        final String sort = "lastname";


        for (Department dep : allDeps()) {
            final List<Paging> pagings = getPagings(filter, sort, String.valueOf(dep.getId()));
            for (Paging paging : pagings) {
                final List<Employee> actual = employeeService.getByDepartmentSortByLastname(dep, paging);
                final List<Employee> expected = readEmployees(
                        Paths.get("src/test/resources", filter, sort, String.valueOf(dep.getId()), dirNameFromPaging(paging))
                );
                assertThat(actual, containsInAnyOrder(expected.toArray()));
                assertThat(expected, containsInAnyOrder(actual.toArray()));
            }
        }
    }

    // By Mgr

    @Test
    public void testByManagerSortByHiredDate() throws IOException {

        final String filter = "by_mgr";
        final String sort = "hired";


        for (Employee mgr : allMgrs()) {
            final List<Paging> pagings = getPagings(filter, sort, String.valueOf(mgr.getId()));
            for (Paging paging : pagings) {
                final List<Employee> actual = employeeService.getByManagerSortByHireDate(mgr, paging);
                final List<Employee> expected = readEmployees(
                        Paths.get("src/test/resources", filter, sort, String.valueOf(mgr.getId()), dirNameFromPaging(paging))
                );
                assertThat(actual, containsInAnyOrder(expected.toArray()));
                assertThat(expected, containsInAnyOrder(actual.toArray()));
            }
        }
    }


    @Test
    public void testByManagerSortByLastname() throws IOException {

        final String filter = "by_mgr";
        final String sort = "lastname";


        for (Employee mgr : allMgrs()) {
            final List<Paging> pagings = getPagings(filter, sort, String.valueOf(mgr.getId()));
            for (Paging paging : pagings) {
                final List<Employee> actual = employeeService.getByManagerSortByLastname(mgr, paging);
                final List<Employee> expected = readEmployees(
                        Paths.get("src/test/resources", filter, sort, String.valueOf(mgr.getId()), dirNameFromPaging(paging))
                );
                assertThat(actual, containsInAnyOrder(expected.toArray()));
                assertThat(expected, containsInAnyOrder(actual.toArray()));
            }
        }
    }


    @Test
    public void testByManagerSortBySalary() throws IOException {

        final String filter = "by_mgr";
        final String sort = "salary";


        for (Employee mgr : allMgrs()) {
            final List<Paging> pagings = getPagings(filter, sort, String.valueOf(mgr.getId()));
            for (Paging paging : pagings) {
                final List<Employee> actual = employeeService.getByManagerSortBySalary(mgr, paging);
                final List<Employee> expected = readEmployees(
                        Paths.get("src/test/resources", filter, sort, String.valueOf(mgr.getId()), dirNameFromPaging(paging))
                );
                assertThat(actual, containsInAnyOrder(expected.toArray()));
                assertThat(expected, containsInAnyOrder(actual.toArray()));
            }
        }
    }

    @Test
    public void testTopNthBySalaryByDepartment() throws IOException {

        for (Department dep : allDeps()) {
            final Path searchPath = Paths.get("src/test/resources", "rank", dep.getId().toString());

            if (Files.notExists(searchPath)){
                continue;
            }

            final Map<Integer, Employee> expectedRankedEmployees = Files.walk(searchPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .collect(Collectors.toMap(
                            path -> Integer.parseInt(path.getParent().getFileName().toString()),
                            this::employeeFrom
                    ));

            final Map<Integer, Employee> actualRankedEmployees = IntStream.range(1, expectedRankedEmployees.size() + 1)
                    .boxed()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            i -> employeeService.getTopNthBySalaryByDepartment(i, dep)
                    ));
            assertEquals(expectedRankedEmployees, actualRankedEmployees);
        }
    }

    @Test
    public void testWithDepartmentAndFullManagerChain() throws IOException {
        final Path searchPath = Paths.get("src/test/resources", "full");
        final Set<Employee> expected = new HashSet<>(readEmployees(searchPath));

        final Set<Employee> actual = employeeService.getAllSortByLastname(page(1).per(1000)).stream()
                .map(emp -> employeeService.getWithDepartmentAndFullManagerChain(emp))
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }


    private List<Paging> getPagings(final String filter, final String sort, final String oddFilter) throws IOException {
        final Path searchPath = Paths.get("src/test/resources", filter, sort, oddFilter);
        if (Files.notExists(searchPath)) {
            return Collections.emptyList();
        }
        return Files.walk(searchPath)
                .filter(Files::isDirectory)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(fileName -> fileName.matches("\\d+_\\d+"))
                .map(this::pagingFromDirName)
                .collect(Collectors.toList());
    }

    private List<Paging> getPagings(final String filter, final String sort) throws IOException {
        return Files.walk(Paths.get("src/test/resources", filter, sort))
                .filter(Files::isDirectory)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(fileName -> fileName.matches("\\d+_\\d+"))
                .map(this::pagingFromDirName)
                .collect(Collectors.toList());
    }

    private <R> Paging pagingFromDirName(final R name) {
        return null;
    }

    private Paging pagingFromDirName(final String dirName) {
        final String[] pagingPair = dirName.split("_");
        return page(Integer.parseInt(pagingPair[0])).per(Integer.parseInt(pagingPair[1]));
    }

    private String dirNameFromPaging(final Paging paging) {
        return paging.page + "_" + paging.itemPerPage;
    }

    private void writeEmployee(final String dir, Employee employee) {
        try {
            if (Files.notExists(Paths.get("src/test/resources", dir))) {
                Files.createDirectories(Paths.get("src/test/resources", dir));
            }
            FileUtils.write(
                    Paths.get("src/test/resources", dir, employee.getId() + ".json").toFile(),
                    Employee.Parser.toJson(employee),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Department> allDeps() {
        try {
            return Files.walk(Paths.get("src/test/resources/dep"))
                    .filter(p -> Files.isRegularFile(p))
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(this::departmentFrom)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Employee> allMgrs() {
        try {
            return Files.walk(Paths.get("src/test/resources/mgr"))
                    .filter(p -> Files.isRegularFile(p))
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(this::employeeFrom)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Employee> allEmps() {
        try {
            return Files.walk(Paths.get("src/test/resources/emp"))
                    .filter(p -> Files.isRegularFile(p))
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(this::employeeFrom)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Department departmentFrom(final Path json) {
        try {
            return Department.Parser.parseJson(FileUtils.readFileToString(json.toFile(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Employee> readEmployees(final Path path) {
        try {
            return Files.walk(path)
                    .filter(p -> Files.isRegularFile(p))
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(this::employeeFrom)
                    .collect(Collectors.toList());
        } catch (Exception e) {
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


    @Test
    public void testNoChangesToReferenceClasses() throws Exception {
        final ImmutableMap<String, String> src2Ref = ImmutableMap.<String, String>builder()
                .put("src/main/java/com/epam/rd/autocode/domain/Department.java", "src/test/resources/ref/Department.java")
                .put("src/main/java/com/epam/rd/autocode/domain/Employee.java", "src/test/resources/ref/Employee.java")
                .put("src/main/java/com/epam/rd/autocode/domain/FullName.java", "src/test/resources/ref/FullName.java")
                .put("src/main/java/com/epam/rd/autocode/domain/Position.java", "src/test/resources/ref/Position.java")
                .put("src/main/java/com/epam/rd/autocode/ConnectionSource.java", "src/test/resources/ref/ConnectionSource.java")
                .put("src/main/resources/init-ddl.sql", "src/test/resources/ref/init-ddl.sql")
                .put("src/main/resources/init-dml.sql", "src/test/resources/ref/init-dml.sql")
                .build();

        src2Ref.forEach((src, ref) -> assertSourceEqualsReference(Paths.get(src), Paths.get(ref)));
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
}