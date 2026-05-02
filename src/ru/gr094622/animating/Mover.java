package ru.gr094622.animating;

import ru.gr094622.model.GeometryObject;

import java.awt.*;
import java.util.List;

public class Mover implements Animatable {
    private List<GeometryObject> objects;
    private Dimension size;
    private boolean isRunning = false;
    private Thread thread;


    public Mover(List<GeometryObject> objects, Dimension size) {
        this.objects = objects;
        this.size = size;
    }

    public void moveFigures() {
        for (var obj : objects) {
            int newX = obj.getX() + obj.getXSpeed();
            int newY = obj.getY() + obj.getYSpeed();

            if (newX < 0 || newX + obj.getSize().width > size.width) {
                obj.inverseXSpeed();
            } else {
                obj.setX(newX);
            }

            if (newY < 0 || newY + obj.getSize().height > size.height) {
                obj.inverseYSpeed();
            } else {
                obj.setY(newY);
            }
        }
    }

    @Override
    public void start() {
        if (isRunning) return;

        isRunning = true;
        thread = new Thread(() -> {
            while (isRunning) {
                moveFigures();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }
}
