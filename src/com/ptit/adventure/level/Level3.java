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
 * Created by TrongThien on 11/8/2016.
 */
public class Level3 extends GameMng {
    private DuongDi duongDi = new DuongDi();
    private Player player = new Player();
    private Random random = new Random();
    private Quai quai;
    private MatTroi matTroi = new MatTroi();
    private SoundMng soundMng = null;
    private CoCayDoc coCayDoc = new CoCayDoc();
    private CoCay coCay = new CoCay();
    private KhungGai khungGai = new KhungGai();
    private HopAn hopAn = new HopAn();

    private ArrayList<DuongDi> duongDis = new ArrayList<>();
    private ArrayList<Quai> quais = new ArrayList<>();
    private ArrayList<MatTroi> matTrois = new ArrayList<>();
    private ArrayList<CoCayDoc> coCayDocs = new ArrayList<>();
    private ArrayList<CoCay> coCays = new ArrayList<>();
    private ArrayList<KhungGai> khungGais = new ArrayList<>();
    private ArrayList<HopAn> hopAns = new ArrayList<>();

    @Override
    public void initGame() {
        int yPlayer = MyFrame.H_FRAME - 107 - 60;
        player = new Player(10, yPlayer);

        int yDuongDi = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - 25;
        int xDuongDi = 0;
        duongDi.setY(yDuongDi);
        for (int i = 1; i < 4; i++) {
            duongDi = new DuongDi(xDuongDi, yDuongDi);
            xDuongDi = i * 400;
            duongDis.add(duongDi);
        }

        int xQuai = MyFrame.W_FRAME - 40;
        int yQuai = 0;
        for (int i = 0; i < 10; i++) {
            quai = new Quai(xQuai, yQuai);
            yQuai += random.nextInt(10) - 250;
            quais.add(quai);
        }

        int xMatTroi = 550;
        int yMatTroi = 0;
        for (int i = 0; i < 1; i++) {
            matTroi = new MatTroi(xMatTroi, yMatTroi);
            matTrois.add(matTroi);
        }
        int xCoDoc = 750;
        int yCoDoc = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - coCayDoc.coCay.getHeight(null);
        for (int i = 0; i < 1; i++) {
            coCayDoc = new CoCayDoc(xCoDoc, yCoDoc);
            coCayDocs.add(coCayDoc);
            xCoDoc += 300;
        }
        int xCo = 100;
        int yCo = MyFrame.H_FRAME - duongDi.duongDi.getHeight(null) - coCay.coCay.getHeight(null);
        for (int i = 0; i < 1; i++) {
            coCay = new CoCay(xCo, yCo);
            coCays.add(coCay);
            xCo += 200;
        }
        for (int i = 0; i < 1; i++) {
            khungGai = new KhungGai(xCoDoc, yCoDoc);
            khungGais.add(khungGai);
        }

        int xHop = duongDi.duongDi.getWidth(null) / 2 + 100;
        int yHop = 210;
        for (int i = 0; i < 1; i++) {
            hopAn = new HopAn(xHop, yHop);
            hopAns.add(hopAn);
        }

        khungGai = new KhungGai(750, yCoDoc);

    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);
        if (player.collisionCoCayDoc(coCayDocs))
            khungGai.drawKhungGai(g2d);

        for (int i = 0; i < duongDis.size(); i++) {
            duongDis.get(i).drawDuongDi(g2d);
        }

        for (int i = 0; i < coCayDocs.size(); i++) {
            coCayDocs.get(i).drawCoCay(g2d);
        }

        for (int i = 0; i < quais.size(); i++) {
            quais.get(i).drawQuai(g2d);
        }
        for (int i = 0; i < matTrois.size(); i++) {
            matTrois.get(i).drawMatTroi(g2d);
        }
        for (int i = 0; i < coCays.size(); i++) {
            coCays.get(i).drawCoCay(g2d);
        }
        for (int i = 0; i < hopAns.size(); i++) {
            if (player.drawHopAn(hopAns)) {
                hopAns.get(i).drawKhung(g2d);
            }
        }

    }

    @Override
    public void move() {
        player.move();
        for (int i = 0; i < quais.size(); i++) {
            quais.get(i).move();
        }
        for (int i = 0; i < matTrois.size(); i++) {
            if (player.moveMatTroi()) {
                matTrois.get(i).move();
            }
        }
    }

    @Override
    public boolean collision() {
        if (player.collisionQuai(quais) || player.collisionMatTroi(matTrois)
                || player.collisionCoCayDoc(coCayDocs) ||player.collisionHopAn(hopAns)) {
            soundMng = new SoundMng();
            soundMng.createSound("src/sound/die.wav");
            soundMng.start();
            int a = JOptionPane.showConfirmDialog(null, "Game over", "Thong bao", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                return true;
            } else System.exit(0);
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
