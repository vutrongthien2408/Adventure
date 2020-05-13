package com.ptit.adventure.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class CoCayDoc {

    private int x;
    private int y;
    private DuongDi duongDi;

    public Image coCay = new ImageIcon(getClass().getResource("/img/co_cay.png")).getImage();

    public CoCayDoc() {
    }

    public CoCayDoc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawCoCay(Graphics2D g2d) {
        // y = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - 20 - vienGach.getHeight(null);
        g2d.drawImage(coCay, x, y,65,65, null);
    }

    public Rectangle getRect(){
        Rectangle rectangle = new Rectangle(x+10,y+2,55,60);
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
