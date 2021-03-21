package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationNoRootsCasesTesting {

    @Parameterized.Parameters
    public static Collection testNumbersForNoRootCases() {
        return Arrays.asList(new Object[][] { { 1321, 2, 1 }, { 43, 2, 2 }, { 19, 1, 3 }, { 22, 3, 4 }, { 23, 4, 5 } });
    }

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;

    public QuadraticEquationNoRootsCasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Test
    public void testNoRootsCase() {
        assertEquals("no roots", quadraticEquation.solve(a, b, c));
    }

}
