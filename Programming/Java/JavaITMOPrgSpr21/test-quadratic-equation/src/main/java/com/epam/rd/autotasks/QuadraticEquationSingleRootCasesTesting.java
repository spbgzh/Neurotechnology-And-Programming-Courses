package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {

    @Parameterized.Parameters
    public static Collection testNumbersForSingleRootCases() {
        return Arrays.asList(
                new Object[][] { { 1.0, 0.0, 0.0, -0.0 }, { 1.0, 2.0, 1.0, -1.0 }, { 2, 0, 0, -0.0 }, { 2, 4, 2, -1.0 }, { 3, 0, 0, -0.0 } });
    }

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;
    private double expectedResult;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expectedResult = expected;
    }

    @Test
    public void testSingleRootCase() {
        assertEquals(String.valueOf(this.expectedResult), quadraticEquation.solve(a, b, c));
    }
}