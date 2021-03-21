# Segments

## Description

Given: class `Point`, skeleton of class `Segment`.

Implement `Segment` methods:

* Constructor, having start and end points as parameters.\
    Ensure that created segment exists and is not degenerative, i.e. start and end of the segment is not the same point.\ 

* `double length()`\
    Return length of the segment.

* `Point middle()`\
    Return a middle point of the segment.
    
* `Point intersection(Segment another)`\
    Return a point of intersection (common point) of the current segment and the given one.\
    Return `null` if there is no such point.\
    Note: if collinear segments shares a single point, return it. 
    If they share a whole non-point subsegment, return `null`.

Hints:
* [Length reference](https://www.wikihow.com/Use-Distance-Formula-to-Find-the-Length-of-a-Line)
* [Midpoint reference](https://www.wikihow.com/Find-the-Midpoint-of-a-Line-Segment#Use-the-Midpoint-Formula)
* [Intersection reference](https://en.wikipedia.org/wiki/Line–line_intersection)
 
## Examples

---
Sample code:
```java
...
double length = new Segment(new Point(0, 0), new Point(3, 4)).length();
System.out.println(length);

```

Output: `5`

---
Sample code:
```java
...
Segment first = new Segment(new Point(0, 0), new Point(4, 4));
Segment second = new Segment(new Point(2, 0), new Point(0, 2));
Point intersection = first.intersection(second);

System.out.println(intersection.getX());
System.out.println(intersection.getY());

```

Output:

```
1
1
```
---
Sample code:
```java
...
Segment seqment = new Segment(new Point(2, 0), new Point(0, 2));
Point midpoint = segment.middle();

System.out.println(midpoint.getX());
System.out.println(midpoint.getY());

```

Output:

```
1
1
```

---
Sample code:
```java
...
Segment first = new Segment(new Point(0, 0), new Point(4, 0));
Segment second = new Segment(new Point(2, 1), new Point(1, 2));
Point intersection = first.intersection(second);

System.out.println(intersection == null);

```

Output:

```
true
```

---

## Skeleton

```java
class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
```

```java
class Segment {

    public Segment(Point start, Point end) {
        //TODO
    }

    double length(){
        //TODO
    }

    Point middle(){
        //TODO
    }

    Point intersection(Segment another){
        //TODO
    }

}
```

## Maven usage
To run tests execute
```shell script
mvn clean test
```