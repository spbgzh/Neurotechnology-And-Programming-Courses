package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SortingTestsTest {

    @Test
    public void testDefaultSorting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(SortingTest.class);

        assertEquals(0, result.getFailureCount());
        assertEquals(5, result.getRunCount());
    }

    @Test
    public void testLazySorting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(LazySortingTestExtension.class);

        assertEquals(2, result.getFailureCount());
        assertEquals(5, result.getRunCount());

        Failure nullCaseFailure = null;
        Failure otherCasesFailure = null;
        for (Failure failure : result.getFailures()) {
            if (failure.getTestHeader().equals("testNullCase(com.epam.rd.autotasks.LazySortingTestExtension)")) {
                nullCaseFailure = failure;
            }
            if (failure.getTestHeader().equals("testOtherCases(com.epam.rd.autotasks.LazySortingTestExtension)")) {
                otherCasesFailure = failure;
            }
        }

        assertNotNull(nullCaseFailure);
        assertNotNull(otherCasesFailure);

        assertThat(nullCaseFailure.getException(), instanceOf(AssertionError.class));
        assertThat(otherCasesFailure.getException(), instanceOf(AssertionError.class));
    }

    @Test
    public void testNullCarelessSorting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(NullCarelessSortingTestExtension.class);

        assertEquals(1, result.getFailureCount());
        assertEquals(5, result.getRunCount());

        Failure nullCaseFailure = null;
        for (Failure failure : result.getFailures()) {
            if (failure.getTestHeader().equals("testNullCase(com.epam.rd.autotasks.NullCarelessSortingTestExtension)")) {
                nullCaseFailure = failure;
            }
        }

        assertNotNull(nullCaseFailure);
        assertThat(nullCaseFailure.getException(), instanceOf(Exception.class));
        assertTrue(nullCaseFailure.getException().getMessage().startsWith("Unexpected exception"));
    }

    @Test
    public void testTrickySorting() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(TrickySortingTestExtension.class);

        assertEquals(4, result.getFailureCount());
        assertEquals(5, result.getRunCount());

        Failure emptyCaseFailure = null;
        Failure singleCaseFailure = null;
        Failure sortedCaseFailure = null;
        Failure otherCasesFailure = null;
        for (Failure failure : result.getFailures()) {
            if (failure.getTestHeader().equals("testSingleElementArrayCase(com.epam.rd.autotasks.TrickySortingTestExtension)")) {
                singleCaseFailure = failure;
            }
            if (failure.getTestHeader().equals("testOtherCases(com.epam.rd.autotasks.TrickySortingTestExtension)")) {
                otherCasesFailure = failure;
            }
            if (failure.getTestHeader().equals("testSortedArraysCase(com.epam.rd.autotasks.TrickySortingTestExtension)")) {
                sortedCaseFailure = failure;
            }
            if (failure.getTestHeader().equals("testEmptyCase(com.epam.rd.autotasks.TrickySortingTestExtension)")) {
                emptyCaseFailure = failure;
            }
        }

        assertNotNull(emptyCaseFailure);
        assertNotNull(singleCaseFailure);
        assertNotNull(sortedCaseFailure);
        assertNotNull(otherCasesFailure);

        assertThat(emptyCaseFailure.getException(), instanceOf(ArrayIndexOutOfBoundsException.class));
        assertThat(singleCaseFailure.getException(), instanceOf(AssertionError.class));
        assertThat(sortedCaseFailure.getException(), instanceOf(AssertionError.class));
        assertThat(otherCasesFailure.getException(), instanceOf(AssertionError.class));
    }

}