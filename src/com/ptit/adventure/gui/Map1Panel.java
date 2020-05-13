package com.ptit.adventure.gui;

import com.ptit.adventure.level.Level1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by TrongThien on 11/3/2016.
 */
public class Map1Panel extends JPanel implements KeyListener, Runnable {

    private Boolean isRungning = true;
    private Level1 level1 = new Level1();
    private MyPanel myPanel;

    public Map1Panel(MyPanel myPanel) {
        this.myPanel = myPanel;
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        level1.initGame();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        level1.draw(g2d);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        level1.setKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level1.clearKey(e.getKeyCode());
    }

    @Override
    public void run() {
        while (isRungning) {
            MainPanel mainPanel = new MainPanel(myPanel);
            if (level1.collision()) {
                myPanel.add(mainPanel,MyPanel.TAG_MAIN);
                myPanel.showPanel(MyPanel.TAG_MAIN);
                isRungning = false;
            }
            level1.move();
//            if (level1.checkWin()) {
//                myPanel.showPanel(MyPanel.TAG_MAP2);
//                int a = JOptionPane.showConfirmDialog(null, "READY!!!", "Thong bao", JOptionPane.YES_NO_OPTION);
//                if (a == JOptionPane.NO_OPTION) {
//                    myPanel.add(mainPanel, MyPanel.TAG_MAIN);
//                    myPanel.showPanel(MyPanel.TAG_MAIN);
//                }
//                isRungning = false;
//            }
            repaint();
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
