package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FactorialTestingsTest {

    @Test
    void testBadInputTesting() {
        TestExecutionSummary summary = Utils.runTesting(FactorialBadInputTesting.class);

        assertEquals(4, summary.getTestsStartedCount(), "You must implement four given test methods in FactorialBadInputTesting");
        assertEquals(4, summary.getTestsSucceededCount(), "All tests must pass for this factorial implementation");
    }


    @Test
    void testCsvParametrizedTesting() throws IOException {
        final Path csvPath = Paths.get("src/main/resources/csvCases.csv");
        assertTrue(Files.exists(csvPath), "You must keep src/main/resources/csvCases.csv unchanged");
        assertEquals("0,1;1,1;2,2;3,6;4,24",
                Files.lines(csvPath).collect(joining(";"))
                , "You must keep src/main/resources/csvCases.csv unchanged");

        TestExecutionSummary summary = Utils.runTesting(FactorialCsvParametrizedTesting.class);

        assertEquals(5, summary.getTestsStartedCount(), "You must implement a parametrized method in FactorialCsvParametrizedTesting");
        assertEquals(5, summary.getTestsSucceededCount(), "All tests must pass for this factorial implementation");
    }


    @Test
    void testMethodSourceParametrizedTesting() {
        final String testCases = FactorialMethodSourceParametrizedTesting.testCases()
                .map(arg -> Arrays.stream(arg.get()).map(Object::toString).collect(joining(",")))
                .collect(joining(";"));
        assertEquals("1,1;2,2;5,120", testCases, "You must specify (1,1; 2,2; 5,120) cases in FactorialMethodSourceParametrizedTesting.testCases() method");

        TestExecutionSummary summary = Utils.runTesting(FactorialMethodSourceParametrizedTesting.class);

        assertEquals(3, summary.getTestsStartedCount(), "You must implement a parametrized method in FactorialMethodSourceParametrizedTesting");
        assertEquals(3, summary.getTestsSucceededCount(), "All tests must pass for this factorial implementation");
    }

    @Test
    void testRegularInputTesting() {
        TestExecutionSummary summary = Utils.runTesting(FactorialMethodSourceParametrizedTesting.class);

        assertThat("You must implement some test methods in FactorialRegularInputTesting",
                summary.getTestsStartedCount(), greaterThan(0L));

        assertThat("All tests must pass for this factorial implementation",
                summary.getTestsStartedCount(), equalTo(summary.getTestsSucceededCount()));
    }

}
