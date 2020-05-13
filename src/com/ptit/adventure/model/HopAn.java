package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class HopAn {
    private int x;
    private int y;


    public Image khung = new ImageIcon(getClass().getResource("/img/khung2.png")).getImage();

    public HopAn() {
    }

    public HopAn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawKhung(Graphics2D g2d) {
        g2d.drawImage(khung, x, y, 50, 50, null);
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

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, 50, 50);
        return rectangle;
    }

}
