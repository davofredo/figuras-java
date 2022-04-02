package com.david.classes.services.figures;

import com.david.classes.abstracts.Figure2D;
import com.david.classes.constants.FigureTypeEnum;
import com.david.classes.constants.Message;
import com.david.classes.domain.figures.*;
import com.david.classes.excepciones.InterruptedProcessException;

import static handlers.InputHandler.getPositiveDouble;

public class FigureService {
    public static Figure2D enterFigureValues(FigureTypeEnum figureType, String unitMeasure) throws InterruptedProcessException {
        Figure2D figure2D = null;
        switch (figureType){
            case CIRCLE:
                double radius = getPositiveDouble((String.format(Message.TYPE_RADIUS, unitMeasure)));
                figure2D = new Circle(radius);
                break;

            case SQUARE:
                double side = getPositiveDouble(String.format(Message.TYPE_SIDE, unitMeasure));
                figure2D = new Square(side);
                break;

            case RECTANGLE:
                double base = getPositiveDouble(String.format(Message.TYPE_BASE, unitMeasure));
                double height = getPositiveDouble(String.format(Message.TYPE_HEIGHT, unitMeasure));
                figure2D = new Rectangle(base, height);
                break;
            case EQUILATERAL_TRIANGLE:
                double equilateralSide = getPositiveDouble(String.format(Message.TYPE_SIDE, unitMeasure));
                figure2D = new EquilateralTriangle(equilateralSide);
                break;
            case ISOSCELES_TRIANGLE:
                double isoscelesSide = getPositiveDouble(String.format(Message.TYPE_SIDE, unitMeasure));
                double isoscelesBase = getPositiveDouble(String.format(Message.TYPE_BASE, unitMeasure));
                figure2D = new IsoscelesTriangle(isoscelesSide, isoscelesBase);
                break;
        }
        return figure2D;
    }
}
