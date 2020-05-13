package com.ptit.adventure.model;

import com.ptit.adventure.gui.MyFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/5/2016.
 */
public class VienGachChet {

    private int x;
    private int y;
    private DuongDi duongDi;

    private Image vienGach = new ImageIcon(getClass().getResource("/img/hop.png")).getImage();

    public VienGachChet() {
    }

    public VienGachChet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawGach(Graphics2D g2d) {
        g2d.drawImage(vienGach, x, y,40,40, null);
    }

    public Rectangle getRect(){
        Rectangle rectangle = new Rectangle(x - 10,y,vienGach.getWidth(null),vienGach.getHeight(null));
        return rectangle;
    }
    public void move(){
        if (y < MyFrame.H_FRAME) {
            y++;
        }
        else y= 0;

    }

}
