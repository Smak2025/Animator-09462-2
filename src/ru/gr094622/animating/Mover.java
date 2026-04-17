package ru.gr094622.animating;

import ru.gr094622.model.GeometryObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Mover implements Animatable, Runnable {
    private List<GeometryObject> objects;
    private JPanel panel;
    private volatile boolean isRunning = false; // volatile: видимость между потоками
    private Thread thread;

    public Mover(List<GeometryObject> objects, JPanel panel) {
        this.objects = objects;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (isRunning) {
            moveFigures();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void moveFigures() {
        int width = panel.getWidth();
        int height = panel.getHeight();
        for (var obj : objects) {
            int newX = obj.getX() + obj.getXSpeed();
            int newY = obj.getY() + obj.getYSpeed();

            // Было size.height для X — баг: должно быть width
            if (newX < 0 || newX + obj.getSize().width > width) {
                obj.inverseXSpeed();
            } else {
                obj.setX(newX);
            }

            if (newY < 0 || newY + obj.getSize().height > height) {
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
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void stop() {
        isRunning = false;
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public Dimension getSize() {
        return panel != null ? panel.getSize() : null;
    }

    @Override
    public void setSize(Dimension size) {
        // размер берётся из panel динамически
    }
}
