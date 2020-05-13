package com.ptit.adventure.gui;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by TrongThien on 11/3/2016.
 */
public class MyFrame extends JFrame implements WindowListener{

    public static final int W_FRAME = 1000;
    public static final int H_FRAME = 500;
    private MyPanel myPanel = new MyPanel();

    public MyFrame(){
        setTitle("Adventure");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(W_FRAME,H_FRAME);
        setResizable(false);
        setLocationRelativeTo(null);
        add(myPanel);
        addWindowListener(this);
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int a = JOptionPane.showConfirmDialog(null,"Tat Nha","Thong Bao",JOptionPane.YES_NO_OPTION);
        if (a==(JOptionPane.YES_OPTION)){
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
