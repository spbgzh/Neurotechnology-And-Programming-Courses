package com.epam.rd.autotasks.paramcareful.incapable;

import com.epam.rd.autotasks.QuadraticEquationTwoRootsCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarefulIncapableQuadraticEquationTwoRootsCasesTesting extends QuadraticEquationTwoRootsCasesTesting {

    public ParamCarefulIncapableQuadraticEquationTwoRootsCasesTesting(final double a, final double b, final double c, final String expected) {
        super(a, b, c, expected);
        quadraticEquation = new ParamCarefulIncapableQuadraticEquation();
    }
}
