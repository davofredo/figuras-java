package com.david.classes.domain.figures;


import com.david.classes.abstracts.Figure2D;
import com.david.classes.constants.Message;

import java.text.NumberFormat;
import java.util.Locale;

public class Rectangle extends Figure2D {

    private double base;
    private double height;

    public Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double calculatePerimeter() {
        return (this.base * 2) + (this.height * 2);
    }

    @Override
    public double calculateArea() {
        return this.base * this.height;
    }

    @Override
    public String getAttributes() {
        StringBuilder attributes = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMaximumFractionDigits(2);
        return attributes
                .append(String.format(Message.FIGURE_HEIGHT, format.format( getHeight())))
                .append(String.format(Message.FIGURE_BASE, format.format(getBase())))
                .toString();
    }
}
