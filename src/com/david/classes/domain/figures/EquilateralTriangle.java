package com.david.classes.domain.figures;

import com.david.classes.abstracts.Triangle;
import com.david.classes.constants.Message;

import java.text.NumberFormat;
import java.util.Locale;

public class EquilateralTriangle extends Triangle {
    private double side;

    public EquilateralTriangle(double lado) {
        this.side = lado;
        this.height = calculateHeight();
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double calculateHeight() {
        return Math.sqrt( Math.pow(side,2) - Math.pow(side /2, 2) );
    }

    @Override
    public double calculatePerimeter() {
        return side * 3;
    }

    @Override
    public double calculateArea() {
        // TODO: Given that all the triangles calculate areas in the same way,
        //  this implementation could be written in the Triangle class.
        //  Also, if by mistake, we call calculateArea, before calling calculateHeight,
        //  the result of this calculation could be wrong. A better implementation would be:
        //  return (side * calculateHeight()) / 2
        return (side * height)/2;
    }

    @Override
    public String getAttributes() {
        StringBuilder attributes = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMaximumFractionDigits(2);
        return attributes
                .append(String.format(Message.FIGURE_SIDES, format.format(getSide())))
                .append(String.format(Message.FIGURE_HEIGHT,  format.format(getHeight())))
                .toString();
    }


}
