package com.epam.rd.autotasks.paramcareless.singlerootonly;

import com.epam.rd.autotasks.QuadraticEquationSingleRootCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarelessSingleRootOnlyQuadraticEquationSingleRootCasesTesting extends QuadraticEquationSingleRootCasesTesting {

    public ParamCarelessSingleRootOnlyQuadraticEquationSingleRootCasesTesting(final double a, final double b, final double c, final double expected) {
        super(a, b, c, expected);
        quadraticEquation = new ParamCarelessSingleRootOnlyQuadraticEquation();
    }
}
