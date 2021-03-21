package com.epam.rd.autotasks;

import com.epam.rd.autotasks.paramcareful.incapable.ParamCarefulIncapableQuadraticEquationNoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareful.incapable.ParamCarefulIncapableQuadraticEquationSingleRootCasesTesting;
import com.epam.rd.autotasks.paramcareful.incapable.ParamCarefulIncapableQuadraticEquationTwoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareful.incapable.ParamCarefulIncapableQuadraticEquationZeroACasesTesting;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParamCarefulIncapableQuadraticEquationTestingTest {


    @Test
    public void testParamCarefulIncapableQuadraticEquationNoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulIncapableQuadraticEquationNoRootsCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testParamCarefulIncapableQuadraticEquationSingleRootCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulIncapableQuadraticEquationSingleRootCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarefulIncapableQuadraticEquationTwoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulIncapableQuadraticEquationTwoRootsCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarefulIncapableQuadraticEquationZeroACasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulIncapableQuadraticEquationZeroACasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }
}
