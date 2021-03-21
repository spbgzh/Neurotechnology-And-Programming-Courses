package com.efimchick.tasks.figures;

abstract class Figure{

    public abstract double area();

    public abstract Point centroid();

    public abstract boolean isTheSame(Figure figure);

    public abstract Point leftPoint();

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) { 
        
    }
}
