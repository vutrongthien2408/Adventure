package com.ptit.adventure.gui;

import com.ptit.adventure.level.Level4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class Map4Panel extends JPanel implements KeyListener, Runnable {

    private Boolean isRungning = true;
    private Level4 level4 = new Level4();
    private MyPanel myPanel;

    public Map4Panel(MyPanel myPanel) {
        this.myPanel = myPanel;
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        level4.initGame();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        level4.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        level4.setKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level4.clearKey(e.getKeyCode());
    }

    @Override
    public void run() {
        while (isRungning) {
            MainPanel mainPanel = new MainPanel(myPanel);
            if (level4.collision()) {
                myPanel.add(mainPanel, MyPanel.TAG_MAIN);
                myPanel.showPanel(MyPanel.TAG_MAIN);
                isRungning = false;
            }
            level4.move();
            if (level4.checkWin()) {
                Map3Panel map3Panel = new Map3Panel(myPanel);
                myPanel.addPanel(map3Panel, MyPanel.TAG_MAP3);
                myPanel.showPanel(MyPanel.TAG_MAP3);
                int a = JOptionPane.showConfirmDialog(null, "READY!!!", "Thong bao", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.NO_OPTION) {
                    myPanel.add(mainPanel, MyPanel.TAG_MAIN);
                    myPanel.showPanel(MyPanel.TAG_MAIN);
                }
                isRungning = false;
            }
            if (level4.checkLose()) {

                int a = JOptionPane.showConfirmDialog(null, "You lose", "Thong bao", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
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
