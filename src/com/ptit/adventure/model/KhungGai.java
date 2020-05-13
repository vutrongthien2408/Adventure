package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/16/2016.
 */
public class KhungGai {
    private int x;
    private int y;


    public Image khung = new ImageIcon(getClass().getResource("/img/khungGai.png")).getImage();

    public KhungGai() {
    }

    public KhungGai(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawKhungGai(Graphics2D g2d) {
        g2d.drawImage(khung, x, y, 65, 65, null);
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
