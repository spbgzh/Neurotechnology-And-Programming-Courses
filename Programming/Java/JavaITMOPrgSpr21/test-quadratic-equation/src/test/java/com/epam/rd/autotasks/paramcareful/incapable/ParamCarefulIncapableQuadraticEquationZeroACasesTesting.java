package com.epam.rd.autotasks.paramcareful.incapable;

import com.epam.rd.autotasks.QuadraticEquationZeroACasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarefulIncapableQuadraticEquationZeroACasesTesting extends QuadraticEquationZeroACasesTesting {
    public ParamCarefulIncapableQuadraticEquationZeroACasesTesting(final double a, final double b, final double c) {
        super(a, b, c);
        quadraticEquation = new ParamCarefulIncapableQuadraticEquation();
    }
}
