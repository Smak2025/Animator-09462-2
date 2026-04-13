package ru.gr094622; // Проверьте соответствие вашему пакету

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Импорты ваших классов
import ru.gr094622.model.GeometryObject;
import ru.gr094622.painting.Circle;
import ru.gr094622.painting.Rect;

public class MainWindow extends JFrame {
    // 1. Определение списка объектов через интерфейс
    private final List<GeometryObject> objects = new ArrayList<>();
    private final JPanel renderPanel;
    private final Mover mover;
    private final Animator animator;

    public MainWindow() {
        setTitle("Geometry Animator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // 2. Инициализация объектов согласно конструкторам (x, y, Dimension, Color)
        Random rnd = new Random();
        objects.add(new Circle(50, 50, new Dimension(40, 40), Color.RED));
        objects.add(new Rect(150, 100, new Dimension(60, 30), Color.BLUE));
        objects.add(new Circle(300, 200, new Dimension(50, 50), new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))));

        // Панель для отрисовки
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

        // Кнопки управления
        JButton btnStart = new JButton("Старт");
        JButton btnStop = new JButton("Стоп");

        JPanel controlPanel = new JPanel();
        controlPanel.add(btnStart);
        controlPanel.add(btnStop);

        add(renderPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // 3. Инициализация логики
        mover = new Mover(objects, renderPanel);
        animator = new Animator(renderPanel);

        new Thread(mover).start();
        new Thread(animator).start();

        btnStart.addActionListener(e -> {
            mover.setRunning(true);
            animator.setRunning(true);
        });

        btnStop.addActionListener(e -> {
            mover.setRunning(false);
            animator.setRunning(false);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}