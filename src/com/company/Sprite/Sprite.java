package com.company.Sprite;

import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Sprite {
    protected ImageIcon img;
    protected Point relativePostition;
    protected Point absolutePosition;

    public void draw(Graphics g) {
        if (relativePostition != null) {
            img.paintIcon(null, g, absolutePosition.x, absolutePosition.y);
        }
    };

    public void setPosition(Point p) {
        setPosition(p.x, p.y);
    }
    public void setPosition(int x, int y) {
        relativePostition = new Point(x, y);
        absolutePosition = new Point((x - 1) * Main.CELL, (y - 1) * Main.CELL);
    };
    public void setNullPosition() {
        relativePostition = null;
        absolutePosition = null;
    }
    public Point getRelativePostition() {
        if (relativePostition == null) {
            return null;
        } else {
            return new Point(relativePostition);
        }
    }

    public abstract String overlap(int x, int y);
}
