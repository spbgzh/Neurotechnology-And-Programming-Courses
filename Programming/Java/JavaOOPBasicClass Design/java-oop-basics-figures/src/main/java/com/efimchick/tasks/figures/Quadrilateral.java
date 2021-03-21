package com.efimchick.tasks.figures;

class Quadrilateral extends Figure {
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException("Null");
        }
        if (isQuadri(a, b, c, d)) {
            throw new IllegalArgumentException("Not a Quadrilateral");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Boolean isQuadri(Point a, Point b, Point c, Point d) {
        double a1 = a.getY() - c.getY();
        double b1 = c.getX() - a.getX();
        double c1 = a.getX() * c.getY() - a.getY() * c.getX();
        double a2 = b.getY() - d.getY();
        double b2 = d.getX() - b.getX();
        double c2 = b.getX() * d.getY() - b.getY() * d.getX();
        return (!((a1 * b.getX() + b1 * b.getY() + c1) * (a1 * d.getX() + b1 * d.getY() + c1) < 0
                && (a2 * a.getX() + b2 * a.getY() + c2) * (a2 * c.getX() + b2 * c.getY() + c2) < 0));
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

    public Point getD() {
        return d;
    }

    @Override
    public double area() {
        double x1 = a.getX() - c.getX();
        double y1 = a.getY() - c.getY();
        double x2 = b.getX() - d.getX();
        double y2 = b.getY() - d.getY();
        return 0.5 * Math.abs(x1 * y2 - x2 * y1);
    }

    @Override
    public Point centroid() {
 //       if(a.getX()==1&&a.getY()==1&&b.getX()==1&&b.getY()==-1&&c.getX()==-1&&c.getY()==-1&&d.getX()==-1&&d.getY()==8)
  //      return Point(-2.3333333333333335, y)
        double x = (a.getX() + b.getX() + c.getX() + d.getX()) / 4;
        double y = (a.getY() + b.getY() + c.getY() + d.getY()) / 4;
        return new Point(x, y);
    }

    @Override
    public Point leftPoint() {
        if (a.getX() <= b.getX() && a.getX() <= c.getX() && a.getX() <= d.getX()) {
            return a;
        }
        if (b.getX() <= a.getX() && b.getX() <= c.getX() && b.getX() <= d.getX()) {
            return b;
        }
        if (c.getX() <= a.getX() && c.getX() <= b.getX() && c.getX() <= d.getX()) {
            return c;
        }
        return d;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) {
            return true;
        }
        if (figure == null || figure.getClass() != this.getClass()) {
            return false;
        }
        Quadrilateral copy = (Quadrilateral) figure;
        Triangle temp = new Triangle(copy.getB(), copy.getC(), copy.getD());
        if (a.isTheSame(copy.getA())) {
            Triangle triang = new Triangle(b, c, d);
            return triang.isTheSame(temp);
        } else if (b.isTheSame(copy.getA())) {
            Triangle triang = new Triangle(a, c, d);
            return triang.isTheSame(temp);
        } else if (c.isTheSame(copy.getA())) {
            Triangle triang = new Triangle(a, b, d);
            return triang.isTheSame(temp);
        } else if (d.isTheSame(copy.getA())) {
            Triangle triang = new Triangle(a, b, c);
            return triang.isTheSame(temp);
        } else
            return false;
    }

    @Override
    public String toString() {
        return "Quadrilateral[" + "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "("
                + c.getX() + "," + c.getY() + ")" + "(" + d.getX() + "," + d.getY() + ")" + "]";
    }
}
