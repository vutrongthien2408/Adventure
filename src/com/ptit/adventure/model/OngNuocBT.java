package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/7/2016.
 */
public class OngNuocBT {

    private int x;
    private int y;
    //private DuongDi duongDi;
    public Image ongNuoc = new ImageIcon(getClass().getResource("/img/ong_nuoc_2.png")).getImage();


    public OngNuocBT(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public OngNuocBT() {
    }

    public void drawOngNuoc(Graphics2D g2d) {
        // y = MyFrame.H_FRAME-duongDi.duongDi.getHeight(null)-20-ongNuoc.getHeight(null);
        g2d.drawImage(ongNuoc,x,y,null);
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x,y,ongNuoc.getWidth(null),ongNuoc.getHeight(null));
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
}
