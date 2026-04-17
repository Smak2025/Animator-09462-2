package ru.gr094622.animating;

import javax.swing.*;
import java.awt.*;

// Animator только триггерит repaint — рисование делает сам panel в paintComponent
public class Animator implements Animatable {

    private volatile boolean isRunning;
    private Thread animationThread;
    private JPanel panel;

    public Animator(JPanel panel) {
        this.panel = panel;
        this.isRunning = false;
    }

    @Override
    public void start() {
        if (isRunning) return;
        isRunning = true;
        animationThread = new Thread(() -> {
            while (isRunning) {
                SwingUtilities.invokeLater(panel::repaint);
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
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
        return panel != null ? panel.getSize() : null;
    }

    @Override
    public void setSize(Dimension size) {
        // размер берётся из panel динамически
    }
}
