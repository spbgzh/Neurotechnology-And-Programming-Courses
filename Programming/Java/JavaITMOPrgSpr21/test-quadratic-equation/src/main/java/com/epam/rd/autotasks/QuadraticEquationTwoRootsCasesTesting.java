package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {

    @Parameterized.Parameters
    public static Collection testNumbersForTwoRootsCases() {
        return Arrays.asList(new Object[][] { { 0.5, -1.5, 1, "2.0 1.0" }, { 1, 0, -4, "2.0 -2.0" },
                { 2, 0, -8, "2.0 -2.0" }, { 3, 0, -27, "3.0 -3.0" } });
    }

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;
    private String[] temp;
    private String expectedResultX1;
    private String expectedResultX2;
    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        String delimiter = " ";
        this.temp = expected.split(delimiter);
        expectedResultX1=temp[0]+" "+temp[1];
        expectedResultX2=temp[1]+" "+temp[0];
    }

    @Test
    public void testTwoRootsCase(){
        String solveAns=quadraticEquation.solve(a, b, c);
        if(expectedResultX1.equals(solveAns)||expectedResultX2.equals(solveAns))
            assertTrue(true);
        else
            assertTrue(false);
    }
}

