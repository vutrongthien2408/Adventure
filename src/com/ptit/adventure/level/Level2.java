package com.ptit.adventure.level;

import com.ptit.adventure.gui.MyFrame;
import com.ptit.adventure.model.*;
import com.ptit.adventure.sound.SoundMng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by TrongThien on 11/4/2016.
 */
public class Level2 extends GameMng {
    private DuongDi duongDi = new DuongDi();
    private Player player;
    private CoCay coCay = new CoCay();
    private CoCayDoc coCayDoc = new CoCayDoc();
    private Random random = new Random();
    private Set set = new Set();
    private SoundMng soundMng = null;
    private KhungGai khungGai =new KhungGai();

    private ArrayList<DuongDi> duongDis = new ArrayList<>();
    private ArrayList<CoCay> coCays = new ArrayList<>();
    private ArrayList<CoCayDoc> coCayDocs = new ArrayList<>();
    private ArrayList<Set> sets = new ArrayList<>();


    @Override
    public void initGame() {
        int yPlayer = MyFrame.H_FRAME - 107 - 60;
        player = new Player(10, yPlayer);
        // tao duong di
        int yDuongDi = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - 25;
        int xDuongDi = 0;
        duongDi.setY(yDuongDi);
        for (int i = 1; i < 3; i++) {
            duongDi = new DuongDi(xDuongDi, yDuongDi);
            xDuongDi = i * 400 + 100;
            duongDis.add(duongDi);
        }

        int xSet = 100;
        int ySet = 0;
        for (int i = 0; i < 6; i++) {
            set = new Set(xSet, ySet);
            xSet += 150;
            ySet -= 50;
            sets.add(set);
        }

        int xCo = 100;
        int yCo = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - coCay.coCay.getHeight(null);
        for (int i = 0; i < 1; i++) {
            coCay = new CoCay(xCo, yCo);
            coCays.add(coCay);
            xCo += 200;
        }

        int xCoDoc = 700;
        int yCoDoc = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - coCayDoc.coCay.getHeight(null);
        for (int i = 0; i < 1; i++) {
            coCayDoc = new CoCayDoc(xCoDoc, yCoDoc);
            coCayDocs.add(coCayDoc);
            xCoDoc += 300;
        }
        khungGai = new KhungGai(700,yCoDoc);
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);
        for (int i = 0; i < duongDis.size(); i++) {
            duongDis.get(i).drawDuongDi(g2d);
        }
        for (int i = 0; i < coCays.size(); i++) {
            coCays.get(i).drawCoCay(g2d);
        }
        for (int i = 0; i < coCayDocs.size(); i++) {
            coCayDocs.get(i).drawCoCay(g2d);
        }
        for (int i = 0; i < sets.size(); i++) {
            sets.get(i).drawSet(g2d);
//            if (sets.get(1).soundSet()){
//                soundMng = new SoundMng();
//                soundMng.createSound("src/sound/smw_thunder.wav");
//                soundMng.start();
//            }
        }
        if (player.collisionCoCayDoc(coCayDocs)){
            khungGai.drawKhungGai(g2d);
        }
    }

    @Override
    public void move() {
        player.move();
        for (int i = 0; i < sets.size(); i++) {
            sets.get(i).move();
        }
    }

    @Override
    public boolean collision() {
        player.collisionDuongDi(duongDis);
        if (player.collisionCoCayDoc(coCayDocs)|| player.collisionSet(sets)) {
            soundMng = new SoundMng();
            soundMng.createSound("src/sound/die.wav");
            soundMng.start();
            int a = JOptionPane.showConfirmDialog(null, "Game over", "Thong bao", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                return true;
            }else System.exit(0);
        }
        return false;
    }

    @Override
    public void setKey(int key) {
        player.getBitSet().set(key);
        if (key == KeyEvent.VK_SPACE) {
            soundMng = new SoundMng();
            soundMng.createSound("src/sound/flap.wav");
            soundMng.start();
        }
    }

    @Override
    public void clearKey(int keyCode) {
        player.getBitSet().clear(keyCode);
    }

    @Override
    public boolean checkWin() {
        if (player.checkWin()) {
            soundMng = new SoundMng();
            soundMng.createSound("src/sound/win.wav");
            soundMng.start();
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLose() {
        if (player.checkLose()){
            return true;
        }
        return false;
    }
}
