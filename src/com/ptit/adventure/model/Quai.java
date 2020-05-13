package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class Quai {
    private int x;
    private int y;


    public Image quai = new ImageIcon(getClass().getResource("/img/quai.png")).getImage();

    public Quai() {
    }

    public Quai(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawQuai(Graphics2D g2d) {
        g2d.drawImage(quai, x, y, 40, 40, null);
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

    public void move() {

        if (y <= 353) {
            y++;
        }
        if (y > 353) {
                x--;
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x-10, y+10, 30, 30);
        return rectangle;
    }
}
