package com.david.classes.domain.figures;


import com.david.classes.abstracts.Figure2D;
import com.david.classes.constants.Message;

import java.text.NumberFormat;
import java.util.Locale;

public class Square extends Figure2D {

    private double side;

    public Square(double lado) {
        this.side = lado;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double calculatePerimeter() {
        return this.side * 4;
    }

    @Override
    public double calculateArea() {
        return Math.pow(this.side, 2);
    }

    @Override
    public String getAttributes() {
        StringBuilder attributes = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMaximumFractionDigits(2);
        return attributes
                .append(String.format(Message.FIGURE_SIDES, format.format(getSide())))
                .toString();
    }


}
