package com.epam.rd.autotasks;

import com.epam.rd.autotasks.paramcareless.tworootsonly.ParamCarelessTwoRootsOnlyQuadraticEquationNoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareless.tworootsonly.ParamCarelessTwoRootsOnlyQuadraticEquationSingleRootCasesTesting;
import com.epam.rd.autotasks.paramcareless.tworootsonly.ParamCarelessTwoRootsOnlyQuadraticEquationTwoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareless.tworootsonly.ParamCarelessTwoRootsOnlyQuadraticEquationZeroACasesTesting;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParamCarelessTwoRootsOnlyQuadraticEquationTestingTest extends QuadraticEquation {


    @Test
    public void testParamCarelessTwoRootsOnlyQuadraticEquationNoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessTwoRootsOnlyQuadraticEquationNoRootsCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarelessTwoRootsOnlyQuadraticEquationSingleRootCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessTwoRootsOnlyQuadraticEquationSingleRootCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarelessTwoRootsOnlyQuadraticEquationTwoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessTwoRootsOnlyQuadraticEquationTwoRootsCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testParamCarelessTwoRootsOnlyQuadraticEquationZeroACasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarelessTwoRootsOnlyQuadraticEquationZeroACasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());

        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

}
