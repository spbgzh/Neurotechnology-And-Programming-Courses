package com.epam.rd.autotasks.paramcareless.tworootsonly;

import com.epam.rd.autotasks.QuadraticEquationSingleRootCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarelessTwoRootsOnlyQuadraticEquationSingleRootCasesTesting extends QuadraticEquationSingleRootCasesTesting {

    public ParamCarelessTwoRootsOnlyQuadraticEquationSingleRootCasesTesting(final double a, final double b, final double c, final double expected) {
        super(a, b, c, expected);
        quadraticEquation = new ParamCarelessTwoRootsOnlyQuadraticEquation();
    }
}
