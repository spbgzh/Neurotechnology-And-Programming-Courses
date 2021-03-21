package com.efimchick.tasks.segments;

public class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Points can not be null");
        }
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException("Start and end should not be the same point");
        }
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    public boolean isOnSegment(Point another) {
        if (start.getX() == another.getX() && start.getY() == another.getY()
                || end.getX() == another.getX() && end.getY() == another.getY()) {
            return true;
        }
        Segment first = new Segment(another, start);
        Segment second = new Segment(another, end);
        double epsilon = 0.00001;
        return Math.abs(first.length() + second.length() - this.length()) < epsilon;
    }

    public Point intersection(Segment another) { // Ax + By + C = 0
        double thisA = start.getY() - end.getY();
        double thisB = end.getX() - start.getX();
        double thisC = start.getX() * end.getY() - end.getX() * start.getY();
        double otherA = another.getStart().getY() - another.getEnd().getY();
        double otherB = another.getEnd().getX() - another.getStart().getX();
        double otherC = another.getStart().getX() * another.getEnd().getY()
                - another.getEnd().getX() * another.getStart().getY();
        double epsilon = 0.00001;
        double determinant = thisA * otherB - otherA * thisB;
        if (Math.abs(determinant) < epsilon) { // 平行或相等
            if (Math.abs(thisC - otherC) < epsilon) { // 在一条直线
                if (Math.abs(thisA) < epsilon) { // 垂直，比较y
                    double testing = (end.getY() - start.getY())
                            * (another.getEnd().getY() - another.getStart().getY());
                    if (Math.abs(start.getY() - another.getStart().getY()) < epsilon) { // 相同的起点
                        if (testing < -epsilon)
                            return start;
                    } else if (Math.abs(start.getY() - another.getEnd().getY()) < epsilon) { // 起点和终点相同
                        if (testing > epsilon)
                            return start;
                    } else if (Math.abs(end.getY() - another.getStart().getY()) < epsilon) { // 终点和起点相同
                        if (testing > epsilon)
                            return end;
                    } else if (Math.abs(end.getY() - another.getEnd().getY()) < epsilon) { // 相同的终点
                        if (testing < -epsilon)
                            return end;
                    }
                    return null;
                } else { // 不垂直比较x
                    double testing = (end.getX() - start.getX())
                            * (another.getEnd().getX() - another.getStart().getX());
                    if (Math.abs(start.getX() - another.getStart().getX()) < epsilon) { // 相同的起点
                        if (testing < -epsilon)
                            return start;
                    } else if (Math.abs(start.getX() - another.getEnd().getX()) < epsilon) { // 起点和终点相同
                        if (testing > epsilon)
                            return start;
                    } else if (Math.abs(end.getX() - another.getStart().getX()) < epsilon) { // 终点和起点相同
                        if (testing > epsilon)
                            return end;
                    } else if (Math.abs(end.getX() - another.getEnd().getX()) < epsilon) { // 相同的终点
                        if (testing < -epsilon)
                            return end;
                    }
                    return null;
                }
            } else { // 平行线
                return null;
            }
        } else { // 不平行
            Point answer = new Point((thisB * otherC - thisC * otherB) / determinant,
                    (otherA * thisC - thisA * otherC) / determinant);
            if (isOnSegment(answer) && another.isOnSegment(answer)) {
                return answer;
            } else {
                return null;
            }
        }
    }
}