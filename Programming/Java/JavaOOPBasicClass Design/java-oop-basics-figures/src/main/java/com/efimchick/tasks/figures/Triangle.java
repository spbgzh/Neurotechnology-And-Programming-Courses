package com.efimchick.tasks.figures;

class Triangle extends Figure {
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("Null");
        }
        double epsilon = 0.00001;
        double x1 = b.getX() - a.getX();
        double x2 = c.getX() - a.getX();
        double y1 = b.getY() - a.getY();
        double y2 = c.getY() - a.getY();
        double area = Math.abs(x1 * y2 - x2 * y1) / 2;
        if (area < epsilon) {
            throw new IllegalArgumentException("Not a Triangle");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    @Override
    public double area() {
        double x1 = b.getX() - a.getX();
        double x2 = c.getX() - a.getX();
        double y1 = b.getY() - a.getY();
        double y2 = c.getY() - a.getY();
        return Math.abs(x1 * y2 - x2 * y1) / 2;
    }

    @Override
    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x, y);
    }

    @Override
    public Point leftPoint() {
        if (a.getX() <= b.getX() && a.getX() <= c.getX()) {
            return a;
        }
        if (b.getX() <= a.getX() && b.getX() <= c.getX()) {
            return b;
        }
        return c;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) {
            return true;
        }
        if (figure == null || this.getClass() != figure.getClass()) {
            return false;
        }
        Triangle copy = (Triangle) figure;
        if (a.isTheSame(copy.getA())) {
            return b.isTheSame(copy.getB()) && c.isTheSame(copy.getC())
                    || b.isTheSame(copy.getC()) && c.isTheSame(copy.getB());
        } else if (a.isTheSame(copy.getB())) {
            return b.isTheSame(copy.getA()) && c.isTheSame(copy.getC())
                    || b.isTheSame(copy.getC()) && c.isTheSame(copy.getA());
        } else if (a.isTheSame(copy.getC())) {
            return b.isTheSame(copy.getA()) && c.isTheSame(copy.getB())
                    || b.isTheSame(copy.getB()) && c.isTheSame(copy.getA());
        } else
            return false;
    }

    @Override
    public String toString() {
        return "Triangle[" + "("+a.getX()+","+a.getY() + ")" + "("+b.getX()+","+b.getY() + ")" + "("+c.getX()+","+c.getY() + ")" + "]";
    }
}
