/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import GameEngine.GameEngine;
import GameEngine.SqlFunctions;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Kaweendra
 */
public class test {

    static int interval;
    static Timer timer;
    SqlFunctions S1= new SqlFunctions();

    public static void main(String[] args) throws IOException {
       String t = SqlFunctions.getMinTime("player1");
        System.out.println(t);
       
        
    }

    private static final int setInterval() {
        if (interval == 1) {
            timer.cancel();
        }
        return --interval;
    }
    
    public static void Timer(){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Input seconds => : ");
        String secs = sc.nextLine();
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println("00:"+setInterval());

            }
        }, delay, period);
    }
}
