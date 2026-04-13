    package ru.gr094622;

    import ru.gr094622.animating.Animator;

    import javax.swing.*;

    public class MainWindow extends JFrame {
        private JPanel mainPanel;
        private Animator animator;
        public MainWindow(){
                setTitle("Анимация геометрических фигур");
                setSize(800, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                // Инициализация списка объектов
                objects = new ArrayList<>();
                objects.add(new Circle(50, 50, 40, 3, 4));
                objects.add(new RectangleShape(200, 150, 60, 40, -5, 3));
                objects.add(new Circle(400, 300, 30, 6, -2));
                objects.add(new RectangleShape(600, 100, 50, 50, -4, -4));

                // Основная панель для отрисовки
                drawPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g); // Очистка фона
                        for (GeometryObject obj : objects) {
                            obj.paint(g);
                        }
                    }
                };
                drawPanel.setBackground(Color.DARK_GRAY);

                // Панель кнопок
                JPanel controlPanel = new JPanel();
                JButton startButton = new JButton("Старт");
                JButton stopButton = new JButton("Стоп");

                controlPanel.add(startButton);
                controlPanel.add(stopButton);

                add(drawPanel, BorderLayout.CENTER);
                add(controlPanel, BorderLayout.SOUTH);

                // Инициализация потоков
                mover = new Mover(objects, drawPanel);
                animator = new Animator(drawPanel);

                Thread moverThread = new Thread(mover);
                Thread animatorThread = new Thread(animator);

                // Делаем потоки даемонами, чтобы они завершались при закрытии окна
                moverThread.setDaemon(true);
                animatorThread.setDaemon(true);

                moverThread.start();
                animatorThread.start();

                // Обработка нажатий
                startButton.addActionListener(e -> {
                    mover.setRunning(true);
                    animator.setRunning(true);
                });

                stopButton.addActionListener(e -> {
                    mover.setRunning(false);
                    animator.setRunning(false);
                });
            }

            public static void main(String[] args) {
                // Запуск UI в потоке диспетчеризации событий (Event Dispatch Thread)
                SwingUtilities.invokeLater(() -> {
                    new MainWindow().setVisible(true);
                });
            }
        }
        }
    }
