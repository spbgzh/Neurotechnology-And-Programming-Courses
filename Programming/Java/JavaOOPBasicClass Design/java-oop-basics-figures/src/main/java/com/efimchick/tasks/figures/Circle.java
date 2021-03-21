package com.efimchick.tasks.figures;

class Circle extends Figure {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        double epsilon = 0.00001;
        if (center == null) {
            throw new IllegalArgumentException("Null");
        }
        if (radius < epsilon) {
            throw new IllegalArgumentException("Not a number");
        }
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public Point leftPoint() {
        return new Point(center.getX() - radius, center.getY());
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) {
            return true;
        }
        if (figure == null || figure.getClass() != this.getClass()) {
            return false;
        }
        Circle copy = (Circle) figure;
        double epsilon = 0.0001;
        return center.isTheSame(copy.getCenter()) && Math.abs(radius - copy.getRadius()) < epsilon;
    }

    public String toString() {
        return "Circle[" + "("+center.getX()+","+center.getY() + ")" + radius + "]";
    }
}
