package com.ptit.adventure.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by TrongThien on 11/10/2016.
 */
public class SoundMng {
    private Clip clip;

    public void createSound(String uri) {
        try {
            File file = new File(uri);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop(int k) { // lap lai k lan
        clip.loop(k);
    }

    public void start() { // bat dau phat nhac
        clip.start();
    }

    public void stop() { // dung phat nhac
        clip.stop();
    }
}
