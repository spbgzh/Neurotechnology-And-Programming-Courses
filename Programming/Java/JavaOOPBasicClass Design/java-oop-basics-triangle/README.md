# Triangle

## Description

Given: class `Point`, skeleton of class `Triangle`.

Implement `Triangle` methods:

* constructor, having three points as parameters.\
    These points refers to vertices of the triangle.\
    Ensure the created triangle exists and it is not degenerative.\
    *NB: Remember, double calculations are not always accurate.*

* `double area()`\
    Return the area of the triangle.
    
* `Point centroid()`\
    Return the centroid of the triangle.  

You may use `main` method of `Triangle` class to tryout your code.

Hints:
* [Triangle existence reference](https://en.wikipedia.org/wiki/Triangle#Existence_of_a_triangle)
* [Triangle area reference](https://en.wikipedia.org/wiki/Triangle#Computing_the_area_of_a_triangle)
* [Centroid reference](https://en.wikipedia.org/wiki/Centroid)

*NB: You may benefit from introducing more classes.*
 
## Examples

---
Sample code:
```java
...
new Triangle(new Point(0,0), new Point(1, 0), new Point(2, 0));
```

Result: Exception because such a triangle would be degenerative.

---
Sample code:
```java
...
double area = new Triangle(new Point(0,0), new Point(3, 0), new Point(0, 4)).area();
System.out.println(area);
```

Output:

```
6.0
```

---
Sample code:
```java
...
Point centroid = new Triangle(new Point(0,0), new Point(3, 0), new Point(0, 3)).centroid();

System.out.println(centroid.getX());
System.out.println(centroid.getY());
```

Output:

```
1.0
1.0
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
class Triangle {
    public Triangle(Point a, Point b, Point c){
        //TODO
    }
    
    public double area(){
        //TODO
    }
    
    public Point centroid(){
        //TODO
    }

    public static void main(String[] args) {
        
    }
}
```