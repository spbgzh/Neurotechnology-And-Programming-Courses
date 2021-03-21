package com.epam.rd.autotasks.paramcareless.singlerootonly;

import com.epam.rd.autotasks.QuadraticEquation;

class ParamCarelessSingleRootOnlyQuadraticEquation extends QuadraticEquation {
    @Override
    public String solve(final double a, final double b, final double c) {
        return String.valueOf((-b / (2 * a)));
    }
}
