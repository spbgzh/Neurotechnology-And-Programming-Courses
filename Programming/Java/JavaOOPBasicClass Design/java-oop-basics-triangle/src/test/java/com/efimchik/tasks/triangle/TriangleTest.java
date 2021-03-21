package com.efimchik.tasks.triangle;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;

public class TriangleTest {

    @Test
    public void testConstructor() {
        new Triangle(new Point(0, 0), new Point(1, 1), new Point(0, 1));
        new Triangle(new Point(-2, 2), new Point(-3, 1), new Point(0, 1));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorNullACase() {
        new Triangle(null, new Point(-3, 1), new Point(0, 1));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorNullBCase() {
        new Triangle(new Point(0, 1), null, new Point(-3, 1));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorNullCCase() {
        new Triangle(new Point(-3, 1), new Point(0, 1), null);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorDegenerative1() {
        new Triangle(new Point(-1, -1), new Point(1, 1), new Point(2, 2));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorDegenerative2() {
        new Triangle(new Point(1, 3), new Point(3, 9), new Point(2, 6));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorDegenerative3() {
        new Triangle(new Point(0, 0), new Point(0, 2), new Point(0, 5));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorDegenerative4() {
        new Triangle(new Point(0, 0), new Point(0, 0), new Point(0, 5));
    }

    @Test
    public void testArea() {
        testArea(6.00, 0, 0, 4, 0, 0, 3);
        testArea(4.50, 0, 1, 0, 4, 3, 0);
        testArea(18.0, 2, 5, -5, 4, 3, 0);
        testArea(35.0, 8, 2, 1, 2, -2, -8);
        testArea(13.0, 4, 5, 2, 5, 3, -8);
        testArea(24.5, 9, 7, 6, 9, 7, -8);
        testArea(7.50, 4, 9, 4, 6, 9, -8);
        testArea(5.00, 6, 3, 7, 3, -3, -7);
        testArea(3.00, 3, 5, 9, 3, 6, 5);
        testArea(10.0, 8, 2, 3, 7, 3, 3);
        testArea(4.00, 7, 7, 4, 0, 5, 5);
        testArea(15.5, 3, 4, 8, 2, 6, 9);
    }

    @Test
    public void testCentroid() {
        testCentroid(0, 0, 4, 0, 0, 3, new Point(1.3333333333333333, 1.0));
        testCentroid(0, 1, 0, 4, 3, 0, new Point(1.0, 1.6666666666666667));
        testCentroid(2, 5, -5, 4, 3, 0, new Point(0.0, 3.0));
        testCentroid(8, 2, 1, 2, -2, -8, new Point(2.3333333333333335, -1.3333333333333333));
        testCentroid(4, 5, 2, 5, 3, -8, new Point(3.0, 0.6666666666666666));
        testCentroid(9, 7, 6, 9, 7, -8, new Point(7.333333333333333, 2.6666666666666665));
        testCentroid(4, 9, 4, 6, 9, -8, new Point(5.666666666666667, 2.3333333333333335));
        testCentroid(6, 3, 7, 3, -3, -7, new Point(3.3333333333333335, -0.3333333333333333));
        testCentroid(3, 5, 9, 3, 6, 5, new Point(6.0, 4.333333333333333));
        testCentroid(8, 2, 3, 7, 3, 3, new Point(4.666666666666667, 4.0));
        testCentroid(7, 7, 4, 0, 5, 5, new Point(5.333333333333333, 4.0));
        testCentroid(3, 4, 8, 2, 6, 9, new Point(5.666666666666667, 5.0));
    }

    private void testArea(final double expected, final double ax, final double ay, final double bx, final double by, final double cx, final double cy) {
        final Triangle t = t(ax, ay, bx, by, cx, cy);
        assertEquals("Error in area() on case " + triangleToString(ax, ay, bx, by, cx, cy), expected, t.area(), 0.0001);
    }

    private void testCentroid(final double ax, final double ay, final double bx, final double by, final double cx, final double cy, final Point expected) {
        final Triangle t = t(ax, ay, bx, by, cx, cy);
        final Point centroid = t.centroid();
        assertEquals("Error in centroid() on case (X) " + triangleToString(ax, ay, bx, by, cx, cy), expected.getX(), t.centroid().getX(), 0.0001);
        assertEquals("Error in centroid() on case (Y) " + triangleToString(ax, ay, bx, by, cx, cy), expected.getY(), t.centroid().getY(), 0.0001);
    }

    private Triangle t(final double ax, final double ay, final double bx, final double by, final double cx, final double cy) {
        return new Triangle(new Point(ax, ay), new Point(bx, by), new Point(cx, cy));
    }

    private String triangleToString(final double ax, final double ay, final double bx, final double by, final double cx, final double cy) {
        return new StringJoiner(";", "[", "]")
                .add(new StringJoiner(";", "(", ")")
                        .add(Double.toString(ax))
                        .add(Double.toString(ay))
                        .toString())
                .add(new StringJoiner(";", "(", ")")
                        .add(Double.toString(bx))
                        .add(Double.toString(by))
                        .toString())
                .add(new StringJoiner(";", "(", ")")
                        .add(Double.toString(cx))
                        .add(Double.toString(cy))
                        .toString())
                .toString();
    }

}