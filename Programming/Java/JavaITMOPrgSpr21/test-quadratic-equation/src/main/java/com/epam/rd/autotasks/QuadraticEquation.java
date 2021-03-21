package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if (a == 0)
            throw new UnsupportedOperationException();
        double delta = b * b - 4 * a * c;
        if (delta<0)
            return "no roots";
        else if (delta == 0) {
            double ans = -b / (2 * a);

            return String.valueOf(ans);
        }
        else{
            double temp= Math.sqrt(delta);
            double x1 = (-b + temp) / (2 * a);
            double x2 = (-b - temp) / (2 * a);
            if (x1 == 0)
                x1 = 0;
            if (x2 == 0)
                x2 = 0;
            return (String.valueOf(x1) + " " + String.valueOf(x2));
        }
    }

}