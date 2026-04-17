package ru.gr094622;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.gr094622.animating.Animator; // Было: импорт отсутствовал
import ru.gr094622.animating.Mover;   // Было: импорт отсутствовал
import ru.gr094622.model.GeometryObject;
import ru.gr094622.painting.Circle;
import ru.gr094622.painting.Rect;

public class MainWindow extends JFrame {
    private final List<GeometryObject> objects = new ArrayList<>();
    private final JPanel renderPanel;
    private final Mover mover;
    private final Animator animator;

    public MainWindow() {
        setTitle("Geometry Animator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        Random rnd = new Random();
        objects.add(new Circle(50, 50, new Dimension(40, 40), Color.RED));
        objects.add(new Rect(150, 100, new Dimension(60, 30), Color.BLUE));
        objects.add(new Circle(300, 200, new Dimension(50, 50), new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))));

        renderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (GeometryObject obj : objects) {
                    obj.paint(g);
                }
            }
        };
        renderPanel.setBackground(Color.WHITE);

        JButton btnStart = new JButton("Старт");
        JButton btnStop = new JButton("Стоп");

        JPanel controlPanel = new JPanel();
        controlPanel.add(btnStart);
        controlPanel.add(btnStop);

        add(renderPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Было: Mover(objects, renderPanel) — конструктор не совпадал
        // Было: Animator(renderPanel) — конструктор не совпадал
        mover = new Mover(objects, renderPanel);
        animator = new Animator(renderPanel);

        // Было: new Thread(mover/animator).start() — Animator не Runnable,
        // и оба класса сами создают поток в start()
        mover.start();
        animator.start();

        // Было: setRunning(true/false) — такого метода нет, есть start()/stop()
        btnStart.addActionListener(e -> {
            mover.start();
            animator.start();
        });

        btnStop.addActionListener(e -> {
            mover.stop();
            animator.stop();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
