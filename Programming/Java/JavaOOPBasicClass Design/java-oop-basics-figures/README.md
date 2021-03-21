# Figures

## Description

Given: class `Point`, abstract class `Figure`, 
skeletons of classes `Triangle`, `Quadrilateral`, `Circle`, ComparatorsCollection.

Make `Triangle`, `Quadrilateral`, `Circle` extend `Figure` class.

### Classes
Implement methods in `Triangle`, `Quadrilateral`, `Circle`:

* constructors with following parameters:

    * `Triangle` - three vertices (points) as parameters.
    
    * `Quadrilateral` - four vertices (points) as parameters.
    
    * `Circle` - point of the center and double value of the radius.
    
    Ensure figures are not degenerative.
    All of them should have non-zero area.
    Quadrilateral is also must be convex.

* `public double area()`\
    Return the area of the figure.
    
* `public Point centroid()`\
    Return the centroid of the figure.\
    Centroid refers to center of mass of the plain figure, not the baricenter.\
    In other words it should be *"area centroid"*.  

* `public String toString()`\
    Return a String value in following formats:
    * `Triangle` - 
        * Format: `Triangle[(a.x,a.y)(b.x,b.y)(c.x,c.y)]`
        * Example: `Triangle[(0.0,0.0)(0.1,5.8)(7.0,7.0)]`    
    * `Quadrilateral` - 
        * Format: `Quadrilateral[(a.x,a.y)(b.x,b.y)(c.x,c.y)(d.x, d.y)]`
        * Example: `Quadrilateral[(0.0,0.0)(0.0,7.1)(7.0,7.0)(7.0,0.0)]`    
    * `Circle` - 
        * Format: `Circle[(center.x,center.y)radius]`
        * Example: `Circle[(0.0,0.6)4.5]`
        
    *NB: you may implement toString() in the `Point` class*

* `public boolean isTheSame(Figure figure)`\
    Two figures are considered to be the same only:
    * if they have the same type
    * and if they completely coincide (e.g. have same vertices). 
    
    *NB: order of the vertices have not to be the same*\
    *NB: remember that double calculations are not always accurate*
    
    *Note for curious: it is almost like `equals()` but it is not. Method `equals` requires consistent behavior alongside `hashCode()` and it is quite complicated to establish in terms of approximate equality like in this exercise*

You may use `main` method of `Figure` class to tryout your code.

Hints:
* [Degeneracy reference](https://en.wikipedia.org/wiki/Degeneracy_(mathematics))
* [Convex quadrilateral reference](https://en.wikipedia.org/wiki/Quadrilateral#Convex_quadrilaterals)
* [Triangle area reference](https://en.wikipedia.org/wiki/Triangle#Computing_the_area_of_a_triangle)
* [Circle area reference](https://en.wikipedia.org/wiki/Circle#Area_enclosed)
* [Quadrilateral area reference](htps://en.wikipedia.org/wiki/Quadrilateral#Area_of_a_convex_quadrilateral)
* [Circle centroid reference](https://www.engineeringintro.com/mechanics-of-structures/centre-of-gravity/centroid-of-circle/)
* [Triangle centroid reference](https://en.wikipedia.org/wiki/Centroid#Of_a_triangle)
* [Quadrilateral centroid reference](https://en.wikipedia.org/wiki/Quadrilateral#Remarkable_points_and_lines_in_a_convex_quadrilateral)
* [Quadrilateral centroid reference 2](https://sites.math.washington.edu/~king/java/gsp/center-mass-quad.html)

### Comparators
Implement methods in `ComparatorsCollection`:

* `public static int compareByArea(Figure lhs, Figure rhs)`\
    You should return:
    * `0` - if figures have equal area
    * `-1` - if `rhs` has larger area than `lhs`   
    * `1` - if `lhs` has larger area than `rhs`
    
    *NB: remember that double calculations are not always accurate*
    
* `public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs)`\
    You should return:
    * `0` - if figures leftmost points has equal horizontal coordinates
    * `-1` - if `rhs` leftmost point lies to the right of the `lhs` leftmost point.   
    * `1` - if `rhs` leftmost point lies to the left of the `lhs` leftmost point.   
    
    *NB: remember that double calculations are not always accurate*
    

* `public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs)`\
    You should return:
    * `0` - if figures centroids has equal horizontal coordinates
    * `-1` - if `rhs` centroid lies to the right of the `lhs` centroid.   
    * `1` - if `rhs` centroid lies to the left of the `lhs` centroid.   
    
    *NB: remember that double calculations are not always accurate*
    


## Examples

---
Sample code:
```java
...
new Triangle(new Point(0,0), new Point(1, 0), new Point(2, 0));
```
Result: Exception because such a figure would be degenerative.

---
Sample code:
```java
...
new Quarilateral(new Point(0,0), new Point(1, 0), new Point(2, 0), new Point(2, 2));
```
Result: Exception because such a figure would be degenerative.

---
Sample code:
```java
...
new Quarilateral(new Point(0,0), new Point(1, 0), new Point(0.1, 0.1), new Point(0, 1));
```
Result: Exception because such a figure would be non-convex.

---
Sample code:
```java
...
double area = new Triangle(new Point(0,0), new Point(3, 0), new Point(0, 4)).area();
System.out.println(area);
```
Output:
```
6
```

---
Sample code:
```java
...
double area = new Quarilateral(new Point(1,0), new Point(2, 1), new Point(1, 2), new Point(0, 1)).area();
System.out.println(area);
```
Output:
```
2
```

---
Sample code:
```java
...
double area = new Circle(new Point(1,1), 3).area();
System.out.println(area);
```
Output:
```
28.274333882308138
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
abstract class Figure {

    public abstract double area();

    public abstract Point centroid();

    public String toString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException();
    }
}
```

```java
//TODO
class Triangle {
    
}
```

```java
//TODO
class Quadrilateral {
    
}
```

```java
//TODO
class Circle {
    
}
```

```java
class ComparatorsCollection {

    //TODO
    public static int compareByArea(Figure lhs, Figure rhs){
        return 0;
    }

    //TODO
    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs){
        return 0;
    }

    //TODO
    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs){
        return 0;
    }
}
```
