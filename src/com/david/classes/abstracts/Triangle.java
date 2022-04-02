package com.david.classes.abstracts;

public abstract class Triangle extends Figure2D {
    protected double height;

    public double getHeight() {
        return height;
    }

    public abstract double calculateHeight();

}