package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.CarelessResourceConsumer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RiskyResourceTest {

    @Test
    public void closedNoException() {
        testCase(0, 0);
        testCase(1, -1);
        testCase(10, -10);
    }

    @Test
    public void closedException() {
        testCase(0, 1);
        testCase(1, 0);
        testCase(10, -9);
    }

    @Test
    public void notClosedException() {
        testCase(0, 2);
        testCase(1, 1);
        testCase(10, -8);
    }

    @Test
    public void notClosednoException() {
        testCase(0, 3);
        testCase(1, 8);
        testCase(10, -12);
    }

    private void testCase(final int input, final int shift) {
        final CloseAwareClosable resource = new CloseAwareClosable();
        new RiskyResource(input, new CarelessResourceConsumer(shift), resource).handleCarelessConsuming();
        assertTrue("Resource is not closed", resource.closed);
    }


}