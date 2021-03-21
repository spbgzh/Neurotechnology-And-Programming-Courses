package com.epam.rd.autotasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {

    @Parameterized.Parameters
    public static Collection testNumbersForZeroACasesCases() {
        return Arrays.asList(
                new Object[][] { { 0, 32.1, 0 }, { 0, 32, 0 }, { 0, 321, 0 }, { 0, 3232, 0 }});
    }

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;
    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Test
    public void testZeroACase() throws UnsupportedOperationException{
        try {
            quadraticEquation.solve(a, b, c);
            fail("didn't throw an exception!");
        } catch (RuntimeException ee) {
            assertTrue(true);
        }
    }
}
