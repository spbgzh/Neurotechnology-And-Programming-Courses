package com.efimchick.tasks.figures;

public class ComparatorsCollection {

    public static int compareByArea(Figure lhs, Figure rhs) {
        double epsilon = 0.00001;
        if (Math.abs(lhs.area() - rhs.area()) < epsilon) {
            return 0;
        }
        if (lhs.area() < rhs.area()) {
            return -1;
        }
        return 1;
    }

    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs) {
        double epsilon = 0.00001;
        if (Math.abs(lhs.leftPoint().getX() - rhs.leftPoint().getX()) < epsilon) {
            return 0;
        }
        if (lhs.leftPoint().getX() < rhs.leftPoint().getX()) {
            return -1;
        }
        return 1;
    }

    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs) {
        double epsilon = 0.00001;
        if (Math.abs(lhs.centroid().getX() - rhs.centroid().getX()) < epsilon) {
            return 0;
        }
        if (lhs.centroid().getX() < rhs.centroid().getX()) {
            return -1;
        }
        return 1;
    }
}
