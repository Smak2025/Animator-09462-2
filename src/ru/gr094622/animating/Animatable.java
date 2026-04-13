package ru.gr094622.animating;

import java.awt.*;

public interface Animatable {
    void start();
    void stop();

    Dimension getSize();
    void setSize(Dimension size);
}
