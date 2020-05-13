package com.ptit.adventure.model;

import com.ptit.adventure.gui.MyFrame;
import com.ptit.adventure.sound.SoundMng;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class Set {
    private int x;
    private int y;
    private SoundMng soundMng = null;

    public Image set = new ImageIcon(getClass().getResource("/img/set.png")).getImage();

    public Set() {
    }

    public Set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawSet(Graphics2D g2d) {
        g2d.drawImage(set, x, y, null);
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
        if (y < MyFrame.H_FRAME ) {
            y += 2;
        } else {
            y = 0;
        }

    }
//    public boolean soundSet(){
//        if(y>=0) {
//            if (y == 0) {
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, set.getWidth(null), set.getHeight(null));
        return rectangle;
    }
}
