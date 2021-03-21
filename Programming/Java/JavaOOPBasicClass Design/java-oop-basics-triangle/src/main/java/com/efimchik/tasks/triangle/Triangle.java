package com.efimchik.tasks.triangle;
class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("null");
        }
        double epsilon = 0.00001;
        double x1 = b.getX() - a.getX();
        double x2 = c.getX() - a.getX();
        double y1 = b.getY() - a.getY();
        double y2 = c.getY() - a.getY();
        double area = Math.abs(x1 * y2 - x2 * y1) / 2; //通过构造矩形
        if (area < epsilon) {
            throw new IllegalArgumentException("Not a triangle");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double x1 = b.getX() - a.getX();
        double x2 = c.getX() - a.getX();
        double y1 = b.getY() - a.getY();
        double y2 = c.getY() - a.getY();
        return Math.abs(x1 * y2 - x2 * y1) / 2;
    }

    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x, y);
    }

    public static void main(String[] args) {

    }
}
