package ru.gr094622.model;

import java.awt.*;
import java.util.Random;

public abstract class AbstractGeometryObject implements GeometryObject{
    protected int x;
    protected int y;
    protected Dimension size;
    protected Color color;
    protected int xSpeed;
    protected int ySpeed;

    public AbstractGeometryObject(int x, int y, int width, int height, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = new Dimension(width, height);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        Random random = new Random();
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getXSpeed() {
        return xSpeed;
    }

    @Override
    public int getYSpeed() {
        return ySpeed;
    }

    @Override
    public void inverseXSpeed() {
        this.xSpeed = -this.xSpeed;
    }

    @Override
    public void inverseYSpeed() {
        this.ySpeed = -this.ySpeed;
    }
    @Override
    public abstract void paint(Graphics g);

}
