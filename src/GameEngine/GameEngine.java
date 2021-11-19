/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.TimerTask;
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
    public GameEngine() {
        thePlayer = this.thePlayer;
        counter = 1;
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

    public String getTimeFromFile() {

        String data = null;
        String score = null;
        try {
            File myObj = new File("src\\GameEngine\\user.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                score = myReader.nextLine();
                System.out.println("Data in file - " + data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return score;
    }

    public void saveUserToFile(String username, String score) throws IOException {

        File file = new File("src\\GameEngine\\user.txt");
        if (file.exists()) {
            //delete file
            file.delete();
        }

        //creating file
        file.createNewFile();

        try {
            FileWriter myWriter = new FileWriter("src\\GameEngine\\user.txt");
            myWriter.write((username) + "\n" + score);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String formatTime(int time) {
        int min = 0;
        int sec = 0;
        String minpart = null;
        String secPart = null;
        String formatTIme = null;

        if (time >= 60) {
            min = time / 60;
            sec = time - (min * 60);
        }else{
            min = 0;
            sec = time;
        }

        if (min == 0) {
            minpart = "00:";
        } else if (min < 10) {
            minpart = "0" + min + ":";
        } else {
            minpart = min + "";
        }

        if(sec>=10){
             secPart = sec+"";
        }else if (sec < 10) {
            secPart = "0" + sec;
        }else if (sec == 0) {
            secPart = "00";
        } 
        else {
            secPart = sec + "";
        }
        
        formatTIme = minpart+secPart;

        return formatTIme;
    }
    
    

}
