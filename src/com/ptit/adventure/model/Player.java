package com.ptit.adventure.model;

import com.ptit.adventure.gui.MainPanel;
import com.ptit.adventure.gui.MyFrame;
import com.ptit.adventure.gui.MyPanel;
import com.ptit.adventure.sound.SoundMng;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by TrongThien on 11/3/2016.
 */
public class Player {
    private int x;
    private int y;
    private long lastTime;
    private int index;

    private SoundMng soundMng = null;

    private BitSet bitSet = new BitSet(256);
    private Image[] images;

    private Image[] right = {
            new ImageIcon(getClass().getResource("/img/di_phai.png")).getImage(),
            new ImageIcon(getClass().getResource("/img/di_phai_2.png")).getImage(),
    };
    private Image[] left = {
            new ImageIcon(getClass().getResource("/img/di_trai.png")).getImage(),
            new ImageIcon(getClass().getResource("/img/di_trai_2.png")).getImage(),
    };
    public Image[] dungIm = {
            new ImageIcon(getClass().getResource("/img/dung_im.png")).getImage()
    };

    private Image[] jumpRight = {
            new ImageIcon(getClass().getResource("/img/nhay_phai.png")).getImage(),
    };
    private Image[] jumpLeft = {
            new ImageIcon(getClass().getResource("/img/nhay_trai.png")).getImage(),
    };

    public Player() {
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void setIndex(Image[] images) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 100) {
            index++;
            if (index >= images.length) {
                index = 0;
            }
            lastTime = currentTime;
        }
    }

    public void draw(Graphics2D g2d) {
        Image[] im = getImages();
        if (!im.equals(images)) {
            index = 0;
            images = im;
        }
        g2d.drawImage(images[index], x, y, 60, 60, null);
        setIndex(images);

    }

    //duongDi.getY() la vi tri dung sat duong di
    private Image[] getImages() {
        if (bitSet.get(KeyEvent.VK_LEFT) && bitSet.get(KeyEvent.VK_SPACE)) {
            return jumpLeft;
        } else if (bitSet.get(KeyEvent.VK_RIGHT) && bitSet.get(KeyEvent.VK_SPACE)) {
            return jumpRight;
        } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
            return right;
        } else if (bitSet.get(KeyEvent.VK_LEFT)) {
            return left;
        } else if (bitSet.get(KeyEvent.VK_SPACE)) {
            return dungIm;
        } else {
            return dungIm;
        }
    }

    int dy = 333;

    //duongDi.getY() la vi tri dung sat duong di
    public void move() {
        if (getImages() == jumpLeft) {
            if (dy >= 190) {
                y--;
                if (x > 0)
                    x--;
                dy = y;

            } else if (dy < 190) {
                if (x > 0)
                    x--;
                y++;
                if (y > 333) {
                    dy = 333;
                }
            }
        } else if (getImages() == jumpRight) {

            if (dy >= 190) {
                y--;
                x++;
                dy = y;

            } else if (dy < 190) {
                x++;
                y++;
                if (y > 333) {
                    dy = 333;
                }
            }

        } else if (getImages() == right) {
            if (y < 333) {
                y++;
            }
            x++;
        } else if (getImages() == left) {
            if (y < 333) {
                y++;
            }
            if (x > 0)
                x--;
        } else if (bitSet.get(KeyEvent.VK_SPACE)) {
            if (dy >= 190) {
                y--;
                dy = y;
            } else if (dy < 190) {
                y++;
                if (y > 333) {
                    dy = 333;
                }
            }
        } else {
            if (y < 333) {
                y++;
            }
        }
    }


    public BitSet getBitSet() {
        return bitSet;
    }

    public Rectangle getRect() {

        Rectangle rectangle = new Rectangle(x+20, y, 20, 60);
        return rectangle;
    }


    public boolean collisionOngNuocAn(ArrayList<OngNuocAn> ongNuocAns) {
        for (int i = 0; i < ongNuocAns.size(); i++) {
            Rectangle rect = getRect().intersection
                    (ongNuocAns.get(i).getRect());
            if (!rect.isEmpty()) {
                if (y >= ongNuocAns.get(i).getY()) {
                    x = ongNuocAns.get(i).getX();
                    y = ongNuocAns.get(i).getY() + 20;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean drawOngNuoc(ArrayList<OngNuocAn> ongNuocAns) {
        for (int i = 0; i < ongNuocAns.size(); i++) {
            Rectangle rect = getRect().intersection
                    (ongNuocAns.get(i).getRect());
            if (!rect.isEmpty()) {
                if (y < ongNuocAns.get(i).getY()) {
                    if (x < ongNuocAns.get(i).getX() + ongNuocAns.get(i).ongNuoc.getWidth(null) - 15) {
                        y = ongNuocAns.get(i).getY() - 60;
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }


    public boolean collisionOngNuocBT(ArrayList<OngNuocBT> ongNuocBTs) {
        for (int i = 0; i < ongNuocBTs.size(); i++) {
            Rectangle rect = getRect().intersection
                    (ongNuocBTs.get(i).getRect());
            if (!rect.isEmpty()) {
                if (y <= ongNuocBTs.get(i).getY() - 40) {
                    if (x < ongNuocBTs.get(i).getX() + ongNuocBTs.get(i).ongNuoc.getWidth(null) - 15) {
                        y = ongNuocBTs.get(i).getY() - 62;
                    }
                } else {
                    x = ongNuocBTs.get(i).getX() - 60;
                    y = ongNuocBTs.get(i).getY() + 20;
                }
                return true;
            }
        }
        return false;
    }

    public boolean collisionDuongDi(ArrayList<DuongDi> duongDis) {
        for (int i = 0; i < duongDis.size() - 1; i++) {
//            if (x > duongDis.get(i).duongDi.getWidth(null) - 15 &&
//                    x < duongDis.get(i + 1).getX() - 35 && y >= 333) {
//                y += 2;
//                return false;
//            }
            Rectangle rectangle = getRect().intersection(duongDis.get(i).getRect());
            if (!rectangle.isEmpty()){
                y-=2;
            }
        }
        return true;
    }

    public boolean collisionVienGachChet(ArrayList<VienGachChet> vienGachChets) {
        for (int i = 0; i < vienGachChets.size(); i++) {
            Rectangle rect = getRect().intersection(
                    vienGachChets.get(i).getRect());
            if (!rect.isEmpty()) {
                y += 2;
                return true;
            }
        }
        return false;
    }

    public boolean collisionCoCayDoc(ArrayList<CoCayDoc> coCayDocs) {
        for (int i = 0; i < coCayDocs.size(); i++) {
            Rectangle rect = getRect().intersection(
                    coCayDocs.get(i).getRect());
            if (!rect.isEmpty()) {
                x = coCayDocs.get(i).getX();
                y = coCayDocs.get(i).getY();
                return true;
            }
        }
        return false;
    }

    public boolean collisionSet(ArrayList<Set> sets) {
        for (int i = 0; i < sets.size(); i++) {
            Rectangle rect = getRect().intersection(sets.get(i).getRect());
            if (!rect.isEmpty()) {
                if (y > sets.get(i).getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collisionQuai(ArrayList<Quai> quais) {
        for (int i = 0; i < quais.size(); i++) {
            Rectangle rectangle = getRect().intersection(quais.get(i).getRect());
            if (!rectangle.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean moveMatTroi() {
        if (x >= 530) {
            if (x == 530) {
                soundMng = new SoundMng();
                soundMng.createSound("src/sound/eat_explo.wav");
                soundMng.start();
            }
            return true;
        } else return false;
    }

    public boolean collisionMatTroi(ArrayList<MatTroi> matTrois) {
        for (int i = 0; i < matTrois.size(); i++) {
            Rectangle rectangle = getRect().intersection(matTrois.get(i).getRect());
            if (!rectangle.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionHopAn(ArrayList<HopAn> hopAns) {
        for (int i = 0; i < hopAns.size(); i++) {
            Rectangle rect = getRect().intersection
                    (hopAns.get(i).getRect());
            if (!rect.isEmpty()) {
                if (y >= hopAns.get(i).getY()) {
                    x = hopAns.get(i).getX();
                    y = hopAns.get(i).getY() + 20;
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public boolean drawHopAn(ArrayList<HopAn> hopAns) {
        for (int i = 0; i < hopAns.size(); i++) {
            Rectangle rect = getRect().intersection
                    (hopAns.get(i).getRect());
            if (!rect.isEmpty()) {
                if (y < hopAns.get(i).getY()) {
                    if (x < hopAns.get(i).getX() + hopAns.get(i).khung.getWidth(null) - 15) {
                        y = hopAns.get(i).getY() - 60;
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkWin() {
        if (y > MyFrame.H_FRAME) {
            return false;
        }
        if (x > MyFrame.W_FRAME) {
            return true;
        }
        return false;
    }

    public boolean checkLose() {
        if (y > MyFrame.H_FRAME) {
            return true;
        }
        return false;
    }
}
