package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DefaultQuadraticEquationTestingTest {

    @Test
    public void testClassesAreParameterized() {
        final List<Class<?>> testingClasses = Arrays.asList(
                QuadraticEquationNoRootsCasesTesting.class,
                QuadraticEquationSingleRootCasesTesting.class,
                QuadraticEquationTwoRootsCasesTesting.class,
                QuadraticEquationZeroACasesTesting.class
        );
        for (Class<?> testingClass : testingClasses) {
            RunWith runWith = testingClass.getAnnotation(RunWith.class);
            assertNotNull(runWith);
            assertEquals(Parameterized.class, runWith.value());
        }
    }

    @Test
    public void testQuadraticEquationNoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(QuadraticEquationNoRootsCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testQuadraticEquationSingleRootCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(QuadraticEquationSingleRootCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testQuadraticEquationTwoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(QuadraticEquationTwoRootsCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testQuadraticEquationZeroACasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(QuadraticEquationZeroACasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

}
