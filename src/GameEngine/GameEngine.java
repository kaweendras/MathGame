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
 * @author Yashodha Hansimali Godage
 */
public class GameEngine {

    private String thePlayer = null;
    private int counter = 1;
    private int totalTime = 0;

    /**
     * Each player has their own game engine.
     *
     * @param player - player's username
     */
    public GameEngine(String player) {
        thePlayer = player;
        counter = 1;
    }

    /**
     * Constructor creates an instance of the
     * class
     */
    public GameEngine() {
        thePlayer = this.thePlayer;
        counter = 1;
    }

    /**
     * getCounter method returns the
     * counter value.
     * @return - counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * This method returns the next image
     * to the game GUI and increase the counter
     * @return
     */
    public String getNextImg() {
        String Img1 = null;

        Img1 = "src\\Question_IMG\\" + counter + ".gif";
        System.out.println(counter);
        counter++;

        return Img1;
    }

    /**
     * This method returns the next image
     * to the game GUI and increase the counter
     * and it takes the image number as a parameter
     * @param i next image number
     * @return image URL
     */
    public String getNextImg(int i) {
        String Img1 = null;
        if (true) {

            Img1 = "src\\Question_IMG\\" + i + ".gif";
            counter++;
        }
        return Img1;
    }

    /**
     * Takes the time from the game
     * and adds it to the total time
     * @param time - Time(Number)
     */
    public void setTotalTime(int time) {

        this.totalTime = this.totalTime + time;
    }

    /**
     * Returns the total time
     * @return time(Number)
     */
    public int getTotalTime() {

        return this.totalTime;
    }

    /**
     * Method takes the File name of the audio file and 
     * plays the audio one time.
     * @param fileName name of the audio file
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException

     */
    public void playAudio(String fileName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File Audio = new File("src\\Audio\\" + fileName + ".wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(Audio);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }

    /**
     * Returns the players time stored in the
     * text file and returns it as the score
     * @return Time(String)
     */
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
    
    
    /**
     * Store the logged in user's score(Total TIme) name
     * in the text file.
     * @param score time/score
     * @throws IOException
     */
    public void saveScoreToFile(String score) throws IOException {

        File file = new File("src\\GameEngine\\user.txt");
        

        //creating file
        file.createNewFile();

        try {
            FileWriter myWriter = new FileWriter("src\\GameEngine\\user.txt");
            myWriter.write((this.thePlayer) + "\n" + score);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Store the logged in user's user name
     * in the text file. The method will delete the file
     * if its exists to clear the data of the previous user
     * @param username username
     * @param score time/score
     * @throws IOException
     */
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

    /**
     *
     * This method take the total time as Seconds
     * converts it to minutes and seconds then returns it
     * @param time time in seconds
     * @return time in Minutes and Seconds
     * 
     */
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
