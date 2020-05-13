package com.ptit.adventure.gui;

import com.ptit.adventure.level.Level2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class Map2Panel extends JPanel implements KeyListener, Runnable {


    private Boolean isRungning = true;
    private Level2 level2 = new Level2();
    private MyPanel myPanel;

    public Map2Panel(MyPanel myPanel) {
        this.myPanel = myPanel;
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        level2.initGame();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        level2.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        level2.setKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level2.clearKey(e.getKeyCode());
    }

    @Override
    public void run() {
        while (isRungning) {
            MainPanel mainPanel = new MainPanel(myPanel);
            if (level2.collision()) {
                myPanel.add(mainPanel,MyPanel.TAG_MAIN);
                myPanel.showPanel(MyPanel.TAG_MAIN);
                isRungning = false;
            }
            level2.move();
            if (level2.checkWin()) {
                Map1Panel map1Panel = new Map1Panel(myPanel);
                myPanel.addPanel(map1Panel, MyPanel.TAG_MAP1);
                myPanel.showPanel(MyPanel.TAG_MAP1);
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
