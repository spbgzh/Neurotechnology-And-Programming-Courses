package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static com.epam.rd.autotasks.Utils.assertFailuresAreAssertionErrors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WrongOperationConcatIntFactorialTestingsTest {

    @Test
    void testBadInputTesting() {
        TestExecutionSummary summary = Utils.runTesting(WrongOperationConcatIntFactorialBadInputTesting.class);

        assertEquals(4, summary.getTestsStartedCount(), "You must implement four given test methods in FactorialBadInputTesting");
        assertEquals(3, summary.getTestsSucceededCount(), "1 test must fail for this factorial implementation");

        assertFailuresAreAssertionErrors(summary);
    }


    @Test
    void testCsvParametrizedTesting() {
        TestExecutionSummary summary = Utils.runTesting(WrongOperationConcatIntFactorialCsvParametrizedTesting.class);

        assertEquals(5, summary.getTestsStartedCount(), "You must implement a parametrized method in FactorialCsvParametrizedTesting");
        assertEquals(2, summary.getTestsSucceededCount(), "2 tests must pass for this factorial implementation");

        assertFailuresAreAssertionErrors(summary);
    }


    @Test
    void testMethodSourceParametrizedTesting() {
        TestExecutionSummary summary = Utils.runTesting(WrongOperationConcatIntFactorialMethodSourceParametrizedTesting.class);

        assertEquals(3, summary.getTestsStartedCount(), "You must implement a parametrized method in FactorialMethodSourceParametrizedTesting");
        assertEquals(1, summary.getTestsSucceededCount(), "2 tests must pass for this factorial implementation");

        assertFailuresAreAssertionErrors(summary);
    }

    @Test
    void testRegularInputTesting() {
        TestExecutionSummary summary = Utils.runTesting(WrongOperationConcatIntFactorialRegularInputTesting.class);

        assertThat("You must implement some test methods in FactorialRegularInputTesting",
                summary.getTestsStartedCount(), greaterThan(0L));

        assertThat("You must consider wrong implementation cases in FactorialRegularInputTesting",
                summary.getTestsFailedCount(), greaterThan(0L));

        assertFailuresAreAssertionErrors(summary);
    }

    private static class WrongOperationConcatIntFactorial extends Factorial {
        @Override
        public String factorial(final String n) {
            int val = Integer.parseInt(n);
            return val <= 1 ? "1" : val + factorial(String.valueOf(val - 1));
        }
    }

    static class WrongOperationConcatIntFactorialBadInputTesting extends FactorialBadInputTesting {
        {
            factorial = new WrongOperationConcatIntFactorial();
        }
    }

    static class WrongOperationConcatIntFactorialCsvParametrizedTesting extends FactorialCsvParametrizedTesting {
        {
            factorial = new WrongOperationConcatIntFactorial();
        }
    }

    static class WrongOperationConcatIntFactorialRegularInputTesting extends FactorialRegularInputTesting {
        {
            factorial = new WrongOperationConcatIntFactorial();
        }
    }

    static class WrongOperationConcatIntFactorialMethodSourceParametrizedTesting extends FactorialMethodSourceParametrizedTesting {
        {
            factorial = new WrongOperationConcatIntFactorial();
        }
    }
}
