package com.epam.rd.autotasks;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class Utils {

    static void printDetails(final TestExecutionSummary summary) {
        System.out.println("summary.getContainersAbortedCount() = " + summary.getContainersAbortedCount());
        System.out.println("summary.getContainersFoundCount() = " + summary.getContainersFoundCount());
        System.out.println("summary.getContainersFailedCount() = " + summary.getContainersFailedCount());
        System.out.println("summary.getContainersSkippedCount() = " + summary.getContainersSkippedCount());
        System.out.println("summary.getContainersStartedCount() = " + summary.getContainersStartedCount());
        System.out.println("summary.getContainersSucceededCount() = " + summary.getContainersSucceededCount());
        System.out.println("summary.getTestsAbortedCount() = " + summary.getTestsAbortedCount());
        System.out.println("summary.getTestsFailedCount() = " + summary.getTestsFailedCount());
        System.out.println("summary.getTestsSkippedCount() = " + summary.getTestsSkippedCount());
        System.out.println("summary.getTestsStartedCount() = " + summary.getTestsStartedCount());
        System.out.println("summary.getTestsSucceededCount() = " + summary.getTestsSucceededCount());
        System.out.println("summary.getTotalFailureCount() = " + summary.getTotalFailureCount());
        for (TestExecutionSummary.Failure failure : summary.getFailures()) {
            System.out.println(failure.getTestIdentifier());
            System.out.println(failure.getException());
        }
    }

    static TestExecutionSummary runTesting(final Class<?> testingClass) {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(testingClass))
                .build();
        Launcher launcher = LauncherFactory.create();
        launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        final TestExecutionSummary summary = listener.getSummary();
        return summary;
    }

    static void assertFailuresAreAssertionErrors(final TestExecutionSummary summary) {
        for (TestExecutionSummary.Failure failure : summary.getFailures()) {
            assertThat(failure.getException(), instanceOf(AssertionError.class));
        }
    }
}
