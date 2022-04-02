package com.david.classes.domain.figures;

import com.david.classes.abstracts.Figure2D;
import com.david.classes.constants.Message;

import java.text.NumberFormat;
import java.util.Locale;

public class Circle extends Figure2D {

    private double radius;

    public Circle(double radio) {
        this.radius = radio;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculatePerimeter() {
        return Math.PI * (this.radius * 2);
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public String getAttributes() {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMaximumFractionDigits(2);
        return String.format(Message.RADIUS_CIRCLE, format.format(getRadius()));
    }

}
