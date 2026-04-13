package ru.gr094622;

import ru.gr094622.animating.Animator;
import ru.gr094622.model.GeometryObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private Animator animator;
    public List<GeometryObject> objects = new ArrayList<>();
    public MainWindow(){

    }
}
