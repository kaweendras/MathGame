/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

/**
 *
 * @author Kaweesha
 */
public class GameEngine {

    private String thePlayer = null;
    private int counter = 1;
    private int totalTime = 0;

    /**
     * Each player has their own game engine.
     *
     * @param player
     */
    public GameEngine(String player) {
        thePlayer = player;
        counter = 1;
    }

    public void setCounter() {
        this.counter = counter++;
    }

    public int getCounter() {
        return counter;
    }

    public String getNextImg() {
        String Img1 = null;
        

            Img1 = "src\\Question_IMG\\" + counter + ".gif";
            System.out.println(counter);
            counter++;
        
        return Img1;
    }

    public String getNextImg(int i) {
        String Img1 = null;
        if (true) {

            Img1 = "src\\Question_IMG\\" + i + ".gif";
            //System.out.println(counter);
            counter++;
        }
        return Img1;
    }

    public void setTotalTime(int time) {

        this.totalTime = this.totalTime + time;
    }

    public int getTotalTime() {

        return this.totalTime;
    }

    public void playAudio(String fileName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File Audio = new File("src\\Audio\\" + fileName + ".wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(Audio);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }
}
