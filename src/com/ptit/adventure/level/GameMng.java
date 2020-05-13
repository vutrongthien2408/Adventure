package com.ptit.adventure.level;

import com.ptit.adventure.sound.SoundMng;

import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public abstract class GameMng {

    public void initGame() {
    }

    public abstract void draw(Graphics2D g2d);

    public abstract void move();

    public abstract boolean collision();

    public abstract void setKey(int key);

    public abstract void clearKey(int keyCode);

    public abstract boolean checkWin();

    public abstract boolean checkLose();

}
