package com.ptit.adventure.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TrongThien on 11/16/2016.
 */
public class MainPanel extends JPanel implements ActionListener{

    private MyPanel myPanel;
    private JButton btnNewGame;
    private JButton btnChonMap;
    private JButton btnExit;
    private Image image = new ImageIcon(getClass().getResource("/img/anhnen.png")).getImage();

    public MainPanel(MyPanel myPanel){
        this.myPanel = myPanel;
        setBackground(Color.WHITE);
        setLayout(null);
        initComp();
    }

    private void initComp() {
        btnNewGame = new JButton();
        btnNewGame.setText("New Game");
        btnNewGame.setBounds(550,100,100,40);
        add(btnNewGame);
        btnNewGame.addActionListener(this);

        btnChonMap = new JButton("Chon Map");
        int yChonMap = btnNewGame.getY() + btnNewGame.getHeight() +20;
        btnChonMap.setBounds(btnNewGame.getX(),yChonMap,100,40);
        add(btnChonMap);

        btnExit = new JButton("Exit");
        int yExit = btnChonMap.getY() + btnChonMap.getHeight() +20;
        btnExit.setBounds(btnNewGame.getX(),yExit,100,40);
        add(btnExit);
        btnExit.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,0,0,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Map4Panel map4Panel = new Map4Panel(myPanel);
        if (e.getSource().equals(btnNewGame)){
            int a = JOptionPane.showConfirmDialog(null,"READY!!!","Thong bao",JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                myPanel.addPanel(map4Panel, MyPanel.TAG_MAP4);
                myPanel.showPanel(MyPanel.TAG_MAP4);
            }
        }
        if(e.getSource().equals(btnExit)){
            System.exit(0);
        }
    }
}
