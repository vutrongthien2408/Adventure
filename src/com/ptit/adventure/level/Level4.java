package com.ptit.adventure.level;

import com.ptit.adventure.gui.MyFrame;
import com.ptit.adventure.gui.MyPanel;
import com.ptit.adventure.model.*;
import com.ptit.adventure.sound.SoundMng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TrongThien on 11/8/2016.
 */
public class Level4 extends GameMng {
    private DuongDi duongDi = new DuongDi();
    private Player player = new Player();
    private OngNuocAn ongNuocAn = new OngNuocAn();
    private OngNuocBT ongNuocBT = new OngNuocBT();
    private MatTroi matTroi = new MatTroi();
    private MyPanel myPanel = new MyPanel();

    private CoCayDoc coCayDoc = new CoCayDoc();
    private Random random = new Random();
    private HopAn hopAn = new HopAn();
    private SoundMng soundMng = null;
    private KhungGai khungGai ;

    private ArrayList<OngNuocAn> ongNuocAns = new ArrayList<>();
    private ArrayList<DuongDi> duongDis = new ArrayList<>();
    private ArrayList<OngNuocBT> ongNuocBTs = new ArrayList<>();
    private ArrayList<MatTroi> matTrois = new ArrayList<>();
    private ArrayList<CoCayDoc> coCayDocs = new ArrayList<>();
    private ArrayList<HopAn> hopAns = new ArrayList<>();
    @Override
    public void initGame() {
        soundMng = new SoundMng();
        soundMng.createSound("src/sound/enter_game.wav");
        soundMng.start();
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

        int yOngNuoc = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - 80 - 25;
        int xOngNuoc = 50;
        for (int i = 0; i < 1; i++) {
            ongNuocAn = new OngNuocAn(xOngNuoc, yOngNuoc);
            xOngNuoc = 30 + random.nextInt(100) + 100;
            ongNuocAns.add(ongNuocAn);
            ongNuocAns.get(i).setX(xOngNuoc);
            ongNuocAns.get(i).setY(yOngNuoc);
        }

        int yOngNuocBT = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - ongNuocBT.ongNuoc.getHeight(null) - 25;
        int xOngNuocBT = 300;
        for (int i = 0; i < 1; i++) {
            ongNuocBT = new OngNuocBT(xOngNuocBT, yOngNuocBT);
            xOngNuocBT = xOngNuocBT + 200;
            ongNuocBTs.add(ongNuocBT);
            ongNuocBTs.get(i).setX(xOngNuocBT);
            ongNuocBTs.get(i).setY(yOngNuocBT);
        }

        int xHop = duongDi.duongDi.getWidth(null) / 2 + 220;
        int yHop = 270;
        for (int i = 0; i < 1; i++) {
            hopAn = new HopAn(xHop, yHop);
            hopAns.add(hopAn);
        }

        int xCoDoc = 450;
        int yCoDoc = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - coCayDoc.coCay.getHeight(null);
        for (int i = 0; i < 1; i++) {
            coCayDoc = new CoCayDoc(xCoDoc, yCoDoc);
            coCayDocs.add(coCayDoc);
            xCoDoc += 300;
            coCayDocs.get(i).setX(xCoDoc);
            coCayDocs.get(i).setY(yCoDoc);
        }
        int xMatTroi = 400;
        int yMatTroi = 0;
        for (int i = 0; i < 1; i++) {
            matTroi = new MatTroi(xMatTroi, yMatTroi);
            matTrois.add(matTroi);
        }
        khungGai = new KhungGai(xCoDoc,yCoDoc);
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);

        for (int i = 0; i < ongNuocBTs.size(); i++) {
            ongNuocBTs.get(i).drawOngNuoc(g2d);
        }

        for (int i = 0; i < ongNuocAns.size(); i++) {
            if (player.drawOngNuoc(ongNuocAns)) {
                ongNuocAns.get(i).drawOngNuoc(g2d);
            }
        }

        for (int i = 0; i < duongDis.size(); i++) {
            duongDis.get(i).drawDuongDi(g2d);
        }

        for (int i = 0; i < hopAns.size(); i++) {
            if (player.drawHopAn(hopAns)) {
                hopAns.get(i).drawKhung(g2d);
            }
        }
        for (int i = 0; i < coCayDocs.size(); i++) {
            coCayDocs.get(i).drawCoCay(g2d);
        }
        for (int i = 0; i < matTrois.size(); i++) {
            matTrois.get(i).drawMatTroi(g2d);
        }

        if (player.collisionCoCayDoc(coCayDocs)){
            khungGai.drawKhungGai(g2d);
        }
    }

    @Override
    public void move() {
        player.move();
    }

    @Override
    public boolean collision() {
        player.collisionOngNuocBT(ongNuocBTs);
        player.collisionDuongDi(duongDis);
        if (player.collisionCoCayDoc(coCayDocs) || player.collisionOngNuocAn(ongNuocAns)
                || player.collisionHopAn(hopAns)) {
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
            soundMng = new SoundMng();
            soundMng.createSound("src/sound/die.wav");
            soundMng.start();
            return true;
        }
        return false;
    }
}
