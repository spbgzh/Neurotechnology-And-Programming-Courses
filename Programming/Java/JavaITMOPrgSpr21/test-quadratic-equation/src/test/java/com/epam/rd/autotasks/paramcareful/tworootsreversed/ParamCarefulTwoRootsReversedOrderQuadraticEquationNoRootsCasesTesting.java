package com.epam.rd.autotasks.paramcareful.tworootsreversed;

import com.epam.rd.autotasks.QuadraticEquationNoRootsCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarefulTwoRootsReversedOrderQuadraticEquationNoRootsCasesTesting extends QuadraticEquationNoRootsCasesTesting {

    public ParamCarefulTwoRootsReversedOrderQuadraticEquationNoRootsCasesTesting(final double a, final double b, final double c) {
        super(a, b, c);
        quadraticEquation = new ParamCarefulTwoRootsReversedOrderQuadraticEquation();
    }
}
