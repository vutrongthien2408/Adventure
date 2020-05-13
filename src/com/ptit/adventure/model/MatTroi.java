package com.ptit.adventure.model;

import com.ptit.adventure.gui.MyFrame;
import com.ptit.adventure.sound.SoundMng;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class MatTroi {
    private int x;
    private int y;

    public Image matTroi = new ImageIcon(getClass().getResource("/img/mat_troi.png")).getImage();

    public MatTroi() {
    }

    public MatTroi(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawMatTroi(Graphics2D g2d) {
        g2d.drawImage(matTroi, x, y, null);
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
        if (y < MyFrame.H_FRAME) {
            y += 2;
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x + 5, y + 5, matTroi.getWidth(null) - 5, matTroi.getHeight(null) - 15);
        return rectangle;
    }
}
