package com.epam.rd.autotasks.paramcareful.tworootsreversed;

import com.epam.rd.autotasks.QuadraticEquationSingleRootCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarefulTwoRootsReversedOrderQuadraticEquationSingleRootCasesTesting extends QuadraticEquationSingleRootCasesTesting {

    public ParamCarefulTwoRootsReversedOrderQuadraticEquationSingleRootCasesTesting(final double a, final double b, final double c, final double expected) {
        super(a, b, c, expected);
        quadraticEquation = new ParamCarefulTwoRootsReversedOrderQuadraticEquation();
    }
}
