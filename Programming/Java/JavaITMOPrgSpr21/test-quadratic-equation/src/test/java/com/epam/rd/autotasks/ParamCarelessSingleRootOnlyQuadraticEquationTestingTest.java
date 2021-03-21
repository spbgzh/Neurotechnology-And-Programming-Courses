package com.epam.rd.autotasks;

import com.epam.rd.autotasks.paramcareless.singlerootonly.ParamCarelessSingleRootOnlyQuadraticEquationNoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareless.singlerootonly.ParamCarelessSingleRootOnlyQuadraticEquationSingleRootCasesTesting;
import com.epam.rd.autotasks.paramcareless.singlerootonly.ParamCarelessSingleRootOnlyQuadraticEquationTwoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareless.singlerootonly.ParamCarelessSingleRootOnlyQuadraticEquationZeroACasesTesting;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParamCarelessSingleRootOnlyQuadraticEquationTestingTest extends QuadraticEquation {

    @Test
    public void testParamCarelessSingleRootOnlyQuadraticEquationNoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessSingleRootOnlyQuadraticEquationNoRootsCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarelessSingleRootOnlyQuadraticEquationSingleRootCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessSingleRootOnlyQuadraticEquationSingleRootCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testParamCarelessSingleRootOnlyQuadraticEquationTwoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessSingleRootOnlyQuadraticEquationTwoRootsCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarelessSingleRootOnlyQuadraticEquationZeroACasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessSingleRootOnlyQuadraticEquationZeroACasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());

        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }
}
