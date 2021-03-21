package com.epam.rd.autotasks.paramcareless.tworootsonly;

import com.epam.rd.autotasks.QuadraticEquationNoRootsCasesTesting;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParamCarelessTwoRootsOnlyQuadraticEquationNoRootsCasesTesting extends QuadraticEquationNoRootsCasesTesting {

    public ParamCarelessTwoRootsOnlyQuadraticEquationNoRootsCasesTesting(final double a, final double b, final double c) {
        super(a, b, c);
        quadraticEquation = new ParamCarelessTwoRootsOnlyQuadraticEquation();
    }
}
