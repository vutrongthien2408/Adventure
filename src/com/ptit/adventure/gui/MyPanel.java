package com.ptit.adventure.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class MyPanel extends JPanel {
    public static final String TAG_MAP1 = "Map1";
    public static final String TAG_MAP2 = "Map2";
    public static final String TAG_MAP3 = "Map3";
    public static final String TAG_MAP4 = "Map4";
    public static final String TAG_MAIN = "Main";

    private Map1Panel map1Panel;
    private Map2Panel map2Panel;
    private Map3Panel map3Panel;
    private Map4Panel map4Panel;
    private MainPanel mainPanel;


    private CardLayout cardLayout = new CardLayout();

    public MyPanel() {
        setLayout(cardLayout);
        mainPanel = new MainPanel(this);
        add(mainPanel);

    }

    public void addPanel(JPanel map,String tag){

//        map3Panel = new Map3Panel(this);
//        add(map3Panel,tag);
//        map4Panel = new Map4Panel(this);
//        add(map4Panel, tag);
        add(map,tag);
    }

    public void showPanel(String tag) {

//        map4Panel = new Map4Panel(this);
//        add(map4Panel, TAG_MAP4);
//
//        map3Panel = new Map3Panel(this);
//        add(map3Panel, TAG_MAP3);
//
//        map2Panel = new Map2Panel(this);
//        add(map2Panel, TAG_MAP2);
//
//        map1Panel = new Map1Panel(this);
//        add(map1Panel, TAG_MAP1);

        cardLayout.show(this, tag);
    }
}
