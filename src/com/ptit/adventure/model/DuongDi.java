package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/4/2016.
 */
public class DuongDi {

    private int x;
    private int y;

    public Image duongDi = new ImageIcon(getClass().getResource("/img/duong_di.png")).getImage();

    public DuongDi() {
    }

    public DuongDi(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawDuongDi(Graphics2D g2d) {
        g2d.drawImage(duongDi, x, y, null);
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
        Rectangle rectangle = new Rectangle(x,y,duongDi.getWidth(null),duongDi.getHeight(null));
        return rectangle;
    }
}
