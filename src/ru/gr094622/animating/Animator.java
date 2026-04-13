package ru.gr094622.animating;

import ru.gr094622.model.GeometryObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Animator implements Animatable {

    private final List<GeometryObject> objects;
    private Dimension size;
    private volatile boolean isRunning;
    private Thread animationThread;
    private BufferedImage buffer;
    private JPanel panel;

    public Animator(List<GeometryObject> objects, Dimension size, JPanel panel) {
        this.objects = new CopyOnWriteArrayList<>(objects);
        this.size = size;
        this.panel = panel;
        this.isRunning = false;
        this.buffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
    }

    private void render() {
        Graphics2D g = buffer.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size.width, size.height);
        for (GeometryObject obj : objects) {
            obj.paint(g);
        }
        g.dispose();
    }

    private void repaint() {
        if (panel != null) {
            SwingUtilities.invokeLater(() -> panel.repaint());
        }
    }

    private void loop() {
        while (isRunning) {
            render();
            repaint();
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start() {
        if (isRunning) return;
        isRunning = true;
        animationThread = new Thread(this::loop);
        animationThread.setDaemon(true);
        animationThread.start();
    }

    @Override
    public void stop() {
        isRunning = false;
        if (animationThread != null) {
            animationThread.interrupt();
        }
    }

    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
        buffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        for (GeometryObject obj : objects) {
            obj.setSize(size);
        }
    }
}