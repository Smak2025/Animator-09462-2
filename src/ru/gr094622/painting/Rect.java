package ru.gr094622.painting;

import ru.gr094622.model.AbstractGeometryObject;

import java.awt.*;

public class Rect extends AbstractGeometryObject {

    public Rect(int x, int y, Dimension size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getSize().width, getSize().height);
    }
}
