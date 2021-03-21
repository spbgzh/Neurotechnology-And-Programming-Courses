package com.epam.rd.autotasks;

import com.epam.rd.autotasks.paramcareful.tworootsreversed.ParamCarefulTwoRootsReversedOrderQuadraticEquationNoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareful.tworootsreversed.ParamCarefulTwoRootsReversedOrderQuadraticEquationSingleRootCasesTesting;
import com.epam.rd.autotasks.paramcareful.tworootsreversed.ParamCarefulTwoRootsReversedOrderQuadraticEquationTwoRootsCasesTesting;
import com.epam.rd.autotasks.paramcareful.tworootsreversed.ParamCarefulTwoRootsReversedOrderQuadraticEquationZeroACasesTesting;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParamCarefulTwoRootsReversedOrderQuadraticEquationTestingTest {


    @Test
    public void testParamCarefulTwoRootsReversedOrderQuadraticEquationNoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulTwoRootsReversedOrderQuadraticEquationNoRootsCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarefulTwoRootsReversedOrderQuadraticEquationSingleRootCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulTwoRootsReversedOrderQuadraticEquationSingleRootCasesTesting.class);

        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
        assertEquals("All the cases must fail on this implementation", result.getRunCount(), result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }

    @Test
    public void testParamCarefulTwoRootsReversedOrderQuadraticEquationTwoRootsCasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulTwoRootsReversedOrderQuadraticEquationTwoRootsCasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

    @Test
    public void testParamCarefulTwoRootsReversedOrderQuadraticEquationZeroACasesTesting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(ParamCarefulTwoRootsReversedOrderQuadraticEquationZeroACasesTesting.class);

        assertEquals(0, result.getFailureCount());
        assertTrue("There must be at least 4 cases for parametrized tests", result.getRunCount() >= 4);
    }

}
