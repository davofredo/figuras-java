package com.david.classes.domain.figures;


import com.david.classes.abstracts.Triangle;
import com.david.classes.constants.Message;

import java.text.NumberFormat;
import java.util.Locale;

public class IsoscelesTriangle extends Triangle {
    private double base;
    private double side;

    public IsoscelesTriangle(double base, double side) {
        this.base = base;
        this.side = side;
        this.height = calculateHeight();
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double calculateHeight() {
        return Math.sqrt(Math.pow(side,2) - (Math.pow((base), 2) / 4 ));
    }

    @Override
    public double calculatePerimeter() {
        return base + (side * 2);
    }

    @Override
    public double calculateArea() {
        return base * height;
    }

    @Override
    public String getAttributes() {
        StringBuilder attributes = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMaximumFractionDigits(2);
        return attributes
                .append(String.format(Message.FIGURE_HEIGHT,  format.format(getHeight())))
                .append(String.format(Message.FIGURE_BASE, format.format(getBase())))
                .append(String.format(Message.FIGURE_SIDES,format.format(getSide())))
                .toString();
    }
}
