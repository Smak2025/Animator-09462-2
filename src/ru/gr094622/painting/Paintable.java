package ru.gr094622.painting;

import java.awt.*;

public interface Paintable {
    Dimension getSize();
    void setSize(Dimension size);
    void paint(Graphics g);
}
