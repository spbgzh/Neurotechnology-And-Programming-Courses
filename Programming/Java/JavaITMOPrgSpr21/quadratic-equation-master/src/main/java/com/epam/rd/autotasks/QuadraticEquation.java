package com.epam.rd.autotasks;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double p = b * b - 4 * a * c;

        if (p > 0) {
            double s1 = Math.sqrt(p);
            double x1 = (-b + s1) / (2 * a);
            double x2 = (-b - s1) / (2 * a);
            if (x1 < x2) {
                System.out.println((-b + s1) / (2 * a) + " " + (-b - s1) / (2 * a));
            } else {
                System.out.println((-b - s1) / (2 * a) + " " + (-b + s1) / (2 * a));
            }
        } else if (p == 0) {
            double s2 = -b / 2 * a;
            System.out.println(s2);
        } else {
            System.out.println("no roots");
        }

        scanner.close();
    }

}