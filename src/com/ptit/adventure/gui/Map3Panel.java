package com.ptit.adventure.gui;

import com.ptit.adventure.level.Level3;
import com.ptit.adventure.sound.SoundMng;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class Map3Panel extends JPanel implements KeyListener, Runnable {

    private Boolean isRungning = true;
    private Level3 level3 = new Level3();
    private MyPanel myPanel;
    private SoundMng soundMng = null;

    public Map3Panel(MyPanel myPanel) {
        this.myPanel = myPanel;
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        level3.initGame();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        level3.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        level3.setKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level3.clearKey(e.getKeyCode());
    }

    @Override
    public void run() {
        while (isRungning) {
            MainPanel mainPanel = new MainPanel(myPanel);
            if (level3.collision()) {
                myPanel.add(mainPanel,MyPanel.TAG_MAIN);
                myPanel.showPanel(MyPanel.TAG_MAIN);
                isRungning = false;
            }
            level3.move();
            if (level3.checkWin()) {
                Map2Panel map2Panel = new Map2Panel(myPanel);
                myPanel.addPanel(map2Panel, MyPanel.TAG_MAP2);
                myPanel.showPanel(MyPanel.TAG_MAP2);
                int a = JOptionPane.showConfirmDialog(null, "READY!!!", "Thong bao", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.NO_OPTION) {
                    myPanel.add(mainPanel, MyPanel.TAG_MAIN);
                    myPanel.showPanel(MyPanel.TAG_MAIN);
                }
                isRungning = false;
            }
            repaint();
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
