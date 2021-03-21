package com.efimchick.tasks.segments;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SegmentTest {

    @Test
    public void testConstructor() {
        new Segment(new Point(0, 0), new Point(1, 1));
        new Segment(new Point(-2, 2), new Point(-3, 1));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorSameStartEndCase() {
        Point p = new Point(3, 7);
        new Segment(p, p);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorEqualStartEndCase() {
        new Segment(new Point(3, 7), new Point(3, 7));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorNullStartCase() {
        new Segment(null, new Point(3, 7));
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorNullEndCase() {
        new Segment(new Point(3, 7), null);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorNullStartEndCase() {
        new Segment(null, null);
    }

    @Test
    public void testLength() {
        testLength(
                new Point(0, 0),
                new Point(3, 4),
                5
        );
        testLength(
                new Point(3, 4),
                new Point(0, 0),
                5
        );
        testLength(
                new Point(-3, 4),
                new Point(0, 0),
                5
        );
        testLength(
                new Point(-3, -4),
                new Point(0, 0),
                5
        );
        testLength(
                new Point(0, 0),
                new Point(3, -4),
                5
        );
        testLength(
                new Point(-3, 8),
                new Point(6, 2),
                Math.sqrt(117)
        );
        testLength(
                new Point(0, 0),
                new Point(1, 1),
                Math.sqrt(2)
        );
        testLength(
                new Point(-2, -2),
                new Point(1, 1),
                Math.sqrt(18)
        );

    }

    @Test
    public void testIntersection() {
        {
            final Segment a = new Segment(new Point(0, 3), new Point(9, 0));
            final Segment b = new Segment(new Point(0, 2), new Point(10, 0));
            final Point expected = new Point(7.5, 0.5);
            testIntersection(expected, a, b);
        }
        {
            final Segment a = new Segment(new Point(0, 0), new Point(3, 4));
            final Segment b = new Segment(new Point(0, 0), new Point(5, 100));
            final Point expected = new Point(0, 0);
            testIntersection(expected, a, b);
        }
        {
            final Segment a = new Segment(new Point(2, 5), new Point(5, 1));
            final Segment b = new Segment(new Point(0, 2), new Point(5, 5));
            final Point expected = new Point(2.9310344827586206, 3.7586206896551726);
            testIntersection(expected, a, b);
        }
        {
            final Segment a = new Segment(new Point(2, 5), new Point(0.5, 1.5));
            final Segment b = new Segment(new Point(0, 2), new Point(5, 5));
            final Point expected = new Point(0.9615384615384616, 2.576923076923077);
            testIntersection(expected, a, b);
        }
        {
            final Segment a = new Segment(new Point(2, 5), new Point(0.5, 1.5));
            final Segment b = new Segment(new Point(0, 2), new Point(2, 5));
            final Point expected = new Point(2, 5);
            testIntersection(expected, a, b);
        }
        {
            final Segment a = new Segment(new Point(-3, 0.5), new Point(0.5, 1.5));
            final Segment b = new Segment(new Point(0, 2), new Point(-3, -1.5));
            final Point expected = new Point(-0.7297297297297297, 1.1486486486486487);
            testIntersection(expected, a, b);
        }
    }

    @Test
    public void testIntersectionCollinear() {
        {
            final Segment a = new Segment(new Point(0, 0), new Point(1, 1));
            final Segment b = new Segment(new Point(1, 1), new Point(2, 2));
            final Point expected = new Point(1, 1);
            testIntersection(expected, a, b);
        }
        {
            final Segment a = new Segment(new Point(0, 0), new Point(1, 1));
            final Segment b = new Segment(new Point(2, 2), new Point(3, 3));
            assertNull(a.intersection(b));
        }
        {
            final Segment a = new Segment(new Point(0, 0), new Point(2, 2));
            final Segment b = new Segment(new Point(2, 2), new Point(1, 1));
            assertNull(a.intersection(b));
        }
    }

    @Test
    public void testIntersectionNone() {
        {
            final Segment a = new Segment(new Point(0, 0), new Point(1, 1));
            final Segment b = new Segment(new Point(-1, -1), new Point(-2, 2));
            final Point actual = a.intersection(b);
            assertNull("Error on case " + pointToString(actual), actual);
        }
        {
            final Segment a = new Segment(new Point(0, 3), new Point(9, 0));
            final Segment b = new Segment(new Point(0, 2), new Point(2, 0));
            final Point actual = a.intersection(b);
            assertNull("Error on case " + pointToString(actual), actual);
        }
        {
            final Segment a = new Segment(new Point(0, 3), new Point(4, 0));
            final Segment b = new Segment(new Point(-1, -3), new Point(1, 1));
            final Point actual = a.intersection(b);
            assertNull("Error on case " + pointToString(actual), actual);
        }
    }

    @Test
    public void testMiddle() {
        testMiddle(new Point(0.5, 0.5), new Segment(new Point(0, 0), new Point(1, 1)));
        testMiddle(new Point(-1.5, 0.5), new Segment(new Point(-1, -1), new Point(-2, 2)));
        testMiddle(new Point(4.5, 1.5), new Segment(new Point(0, 3), new Point(9, 0)));
        testMiddle(new Point(1, 1), new Segment(new Point(0, 2), new Point(2, 0)));
        testMiddle(new Point(2, 1.5), new Segment(new Point(0, 3), new Point(4, 0)));
        testMiddle(new Point(0, -1), new Segment(new Point(-1, -3), new Point(1, 1)));
        testMiddle(new Point(0, 2), new Segment(new Point(0, 1), new Point(0, 3)));
    }

    private void testMiddle(final Point expected, final Segment segment) {
        final Point actual = segment.middle();
        assertEquals(expected.getX(), actual.getX(), 0.001);
        assertEquals(expected.getY(), actual.getY(), 0.001);
    }

    private void testIntersection(final Point expected, final Segment a, final Segment b) {
        final Point actual = a.intersection(b);
        assertEquals(expected.getX(), actual.getX(), 0.001);
        assertEquals(expected.getY(), actual.getY(), 0.001);
    }

    private void testLength(final Point start, final Point end, final double expected) {
        double length = new Segment(start, end).length();
        assertEquals("Error on " + segmentCaseToString(start, end), expected, length, 0.001);
    }

    private String segmentCaseToString(Point start, Point end) {
        return new StringJoiner("->", "[", "]")
                .add(pointToString(start))
                .add(pointToString(end))
                .toString();
    }

    private String pointToString(final Point point) {
        if (point == null) {
            return null;
        }
        return new StringJoiner(";", "(", ")")
                .add(Double.toString(point.getX()))
                .add(Double.toString(point.getY()))
                .toString();
    }

}