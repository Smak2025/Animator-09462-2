package ru.gr094622.model;

import java.awt.*;

public interface GeometryObject{
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    Dimension getSize();
    void setSize(Dimension size);
    Color getColor();

    int getXSpeed();

    int getYSpeed();

    void inverseXSpeed();
    void inverseYSpeed();

    void paint(Graphics g);
}
