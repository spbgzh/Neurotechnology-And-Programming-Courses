package com.epam.rd.autotasks.springemployeecatalog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MvcApplication.class)
@AutoConfigureMockMvc
public class MvcTests {
    private static final String SUITE = "mvc";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testAll() throws Exception {
        Path testCaseFile = Paths.get(SUITE, "all", "all.json");
        Utils.testCaseRoutine(testCaseFile, testCase -> {
            final MvcResult res = mockMvc.perform(get("/employees"))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();

            mockMvc.perform(get("/employees?sort=lastName"))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false));
        });
    }

    @Test
    public void testAllPages() throws Exception {
        testAllPage(0, 2, "lastName");
        testAllPage(1, 3, "lastName");
        testAllPage(2, 4, "lastName");
        testAllPage(0, 2, "hired");
        testAllPage(1, 3, "hired");
        testAllPage(2, 4, "hired");
        testAllPage(9, 1, "position");
        testAllPage(0, 2, "position");
        testAllPage(4, 1, "salary");
        testAllPage(2, 3, "salary");
        testAllPage(2, 5, "salary");
    }

    @Test
    public void testById() throws Exception {
        testById(7839L);
        testById(7698L);
        testById(7782L);
        testById(7566L);
        testById(7788L);
        testById(7902L);
        testById(7369L);
        testById(7499L);
        testById(7521L);
        testById(7654L);
        testById(7844L);
        testById(7876L);
        testById(7900L);
        testById(7934L);

        testByIdFullChain(7839L);
        testByIdFullChain(7698L);
        testByIdFullChain(7782L);
        testByIdFullChain(7566L);
        testByIdFullChain(7788L);
        testByIdFullChain(7902L);
        testByIdFullChain(7369L);
        testByIdFullChain(7499L);
        testByIdFullChain(7521L);
        testByIdFullChain(7654L);
        testByIdFullChain(7844L);
        testByIdFullChain(7876L);
        testByIdFullChain(7900L);
        testByIdFullChain(7934L);
    }

    @Test
    public void testByManager() throws Exception {

        int[] mgrs = {7839, 7698, 7782, 7566, 7788, 7902, 7369};

        for (int mgr : mgrs) {
            testMgrPage(mgr, 0, 10, "lastName");
            testMgrPage(mgr, 0, 2, "lastName");
            testMgrPage(mgr, 1, 1, "lastName");
            testMgrPage(mgr, 0, 3, "hired");
            testMgrPage(mgr, 2, 1, "hired");
            testMgrPage(mgr, 0, 1, "salary");
            testMgrPage(mgr, 1, 2, "salary");
        }
    }

    @Test
    public void testByDepName() throws Exception {

        String[] deps = {"ACCOUNTING", "RESEARCH", "SALES", "OPERATIONS"};

        for (String dep : deps) {
            testDepPage(dep, 0, 10, "lastName");
            testDepPage(dep, 0, 2, "lastName");
            testDepPage(dep, 1, 1, "lastName");
            testDepPage(dep, 0, 3, "hired");
            testDepPage(dep, 2, 1, "hired");
            testDepPage(dep, 0, 1, "salary");
            testDepPage(dep, 1, 2, "salary");
        }
    }

    @Test
    public void testByDepId() throws Exception {

        int[] deps = {10, 20, 30, 40};

        for (int dep : deps) {
            testDepPage(dep, 0, 10, "lastName");
            testDepPage(dep, 0, 2, "lastName");
            testDepPage(dep, 1, 1, "lastName");
            testDepPage(dep, 0, 3, "hired");
            testDepPage(dep, 2, 1, "hired");
            testDepPage(dep, 0, 1, "salary");
            testDepPage(dep, 1, 2, "salary");
        }
    }

    private void testById(final Long id) throws Exception {
        final Path testCaseFile = Paths.get(SUITE, "byId", String.format("%s.json", id));

        Utils.testCaseRoutine(testCaseFile, testCase -> {
            final MvcResult res = mockMvc.perform(get("/employees/{id}", id))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();
        });

    }

    private void testByIdFullChain(final Long id) throws Exception {
        final Path testCaseFile = Paths.get(SUITE, "byId", String.format("%s-full.json", id));
        Utils.testCaseRoutine(testCaseFile, testCase -> {
            final MvcResult res = mockMvc.perform(get("/employees/{id}?full_chain=true", id))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();
        });

        final Path testCaseFileFull = Paths.get(SUITE, "byId", String.format("%s.json", id));

        Utils.testCaseRoutine(testCaseFileFull, testCase -> {
            mockMvc.perform(get("/employees/{id}?full_chain=false", id))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();
        });
    }

    private void testAllPage(final int page,
                             final int size,
                             final String sortField) throws Exception {

        final Path testCaseFile = Paths.get(SUITE, "all", String.format("%s-%s-%s.json", sortField, page, size));

        Utils.testCaseRoutine(testCaseFile, testCase -> {

            final MvcResult res = mockMvc.perform(get("/employees?page={page}&size={size}&sort={sortField}", page, size, sortField))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();

        });

    }

    private void testMgrPage(final int mgr,
                             final int page,
                             final int size,
                             final String sortField) throws Exception {
        final Path testCaseFile = Paths.get("mvc/byMgr", String.format("%s-%s-%s-%s.json", mgr, sortField, page, size));

        Utils.testCaseRoutine(testCaseFile, testCase -> {
            final MvcResult res = mockMvc.perform(get(
                    "/employees/by_manager/{mgr}?page={page}&size={size}&sort={sortField}",
                    mgr, page, size, sortField))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();
        });

    }

    private void testDepPage(final String dep,
                             final int page,
                             final int size,
                             final String sortField) throws Exception {
        final Path testCaseFile = Paths.get(SUITE, "byDep", String.format("%s-%s-%s-%s.json", dep, sortField, page, size));

        Utils.testCaseRoutine(testCaseFile, testCase -> {
            final MvcResult res = mockMvc.perform(get(
                    "/employees/by_department/{dep}?page={page}&size={size}&sort={sortField}",
                    dep, page, size, sortField))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();
        });

    }

    private void testDepPage(final int dep,
                             final int page,
                             final int size,
                             final String sortField) throws Exception {
        final Path testCaseFile = Paths.get(SUITE, "byDep", String.format("%s-%s-%s-%s.json", dep, sortField, page, size));

        Utils.testCaseRoutine(testCaseFile, testCase -> {
            final MvcResult res = mockMvc.perform(get(
                    "/employees/by_department/{dep}?page={page}&size={size}&sort={sortField}",
                    dep, page, size, sortField))
                    .andDo(log())
                    .andExpect(
                            status().isOk())
                    .andExpect(
                            content().json(Utils.readTestResourceFile(testCase), false))
                    .andReturn();
        });

    }
}
