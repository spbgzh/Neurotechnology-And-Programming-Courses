package com.epam.rd.autotasks.paramcareful.incapable;

import com.epam.rd.autotasks.QuadraticEquationNoRootsCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarefulIncapableQuadraticEquationNoRootsCasesTesting extends QuadraticEquationNoRootsCasesTesting {

    public ParamCarefulIncapableQuadraticEquationNoRootsCasesTesting(final double a, final double b, final double c) {
        super(a, b, c);
        quadraticEquation = new ParamCarefulIncapableQuadraticEquation();
    }
}
