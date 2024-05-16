package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/soundtrack.wav");
        soundURL[1] = getClass().getResource("/sound/demonz_duck.wav");
        soundURL[2] = getClass().getResource("/sound/collect.wav");
        soundURL[3] = getClass().getResource("/sound/chest_open.wav");
        soundURL[4] = getClass().getResource("/sound/door_open.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e){
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
