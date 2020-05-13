package com.ptit.adventure.model;

import com.ptit.adventure.gui.MyFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/5/2016.
 */
public class OngNuocAn {
    private int x;
    private int y;
    //private DuongDi duongDi;
    public Image ongNuoc = new ImageIcon(getClass().getResource("/img/hopGai.png")).getImage();

    public OngNuocAn() {
    }

    public OngNuocAn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawOngNuoc(Graphics2D g2d) {
        // y = MyFrame.H_FRAME-duongDi.duongDi.getHeight(null)-20-ongNuoc.getHeight(null);
        g2d.drawImage(ongNuoc, x, y,54,80, null);
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, 54,80);
        return rectangle;
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
        if (x + ongNuoc.getWidth(null) > 0) {
            x--;
        } else x = MyFrame.W_FRAME;
    }
}
