package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/5/2016.
 */
public class CoCay {
    private int x;
    private int y;
    public Image coCay = new ImageIcon(getClass().getResource("/img/co_cay.png")).getImage();

    public CoCay() {
    }

    public CoCay(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawCoCay(Graphics2D g2d) {
        g2d.drawImage(coCay, x, y,65,65, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

