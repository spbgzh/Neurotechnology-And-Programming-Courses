package com.epam.rd.autotasks.paramcareless.singlerootonly;

import com.epam.rd.autotasks.QuadraticEquationNoRootsCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarelessSingleRootOnlyQuadraticEquationNoRootsCasesTesting extends QuadraticEquationNoRootsCasesTesting {

    public ParamCarelessSingleRootOnlyQuadraticEquationNoRootsCasesTesting(final double a, final double b, final double c) {
        super(a, b, c);
        quadraticEquation = new ParamCarelessSingleRootOnlyQuadraticEquation();
    }
}
