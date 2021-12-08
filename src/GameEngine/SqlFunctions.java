package GameEngine;

import DbConnection.connection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yashodha Hansimali Godage 
 * 
 * This Class contains all the methods related to
 * SQL/Database
 */
public class SqlFunctions {

    private String username;
    private String name;
    private String score;

    /**
     * Login take two parameters and check them in the database
     * to see the whether the user is already exist in the database
     * @param username username
     * @param password password
     * @return True/False
     */
    public boolean login(String username, String password) {
        boolean b = false;
        try {

            ResultSet rs = connection.getData("SELECT * FROM users WHERE username='" + username + "'");
            while (rs.next()) {
                String uname = rs.getString("username");
                String pass = rs.getString("password");

                if (username.equals(uname) && password.equals(pass)) {
                    b = true;

                    this.username = rs.getString("username");
                    System.out.println(username);
                    this.name = rs.getString("name");
                    saveUserToFile();//save username to file

                } else {
                    b = false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    /**
     *
     * Returns the Name of the Logged user
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Returns the Username of the Logged user
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username of the logged in user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Register function takes 3 parameters and create a new
     * user by entering the above mentioned data to the database.
     * @param uname username
     * @param name player's name
     * @param Password password
     * @throws java.io.IOException
     */
    public void Register(String uname, String name, String Password) throws IOException {
        try {
            connection.setData("insert into users(username,name,password) value('" + uname + "', '" + name + "','" + Password + "')");
        } catch (Exception ex) {
            Logger.getLogger(SqlFunctions.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        this.name = name;
        this.username = uname;

        saveUserToFile();//save username to file

        JOptionPane.showMessageDialog(null, "Player: " + name + "  Created Successfully");

    }

    /**
     * takes the number as the question ID and returns the
     * corresponding question in the database as a String
     * @param qid question Id
     * @return question
     */
    public String getQuestion(int qid) {
        String question = null;
        try {

            ResultSet rs = connection.getData("SELECT question FROM questions WHERE qid='" + qid + "'");
            while (rs.next()) {
                question = rs.getString("question");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }

    /**
     * takes the number as the question ID and returns the
     * corresponding Answer in the database as a String
     * @param qid question ID
     * @return Answer
     */
    public String getAnswer(int qid) {
        String answer = null;
        try {

            ResultSet rs = connection.getData("SELECT answer FROM questions WHERE qid='" + qid + "'");
            while (rs.next()) {
                answer = rs.getString("answer");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * takes the logged in user as a parameter and saves it to the
     * file
     * @throws IOException
     */
    public void saveUserToFile() throws IOException {

        File file = new File("src\\GameEngine\\user.txt");
        if (file.exists()) {
            //delete file
            file.delete();
        }

        //creating file
        file.createNewFile();

        try {
            FileWriter myWriter = new FileWriter("src\\GameEngine\\user.txt");
            myWriter.write(getUsername() + "\n0");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * returns the user stored in the file
     * as a String
     * @return
     */
    public String readFromFile() {
        String data = null;
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

        return data;
    }

    /**
     * Takes the user and score as parameters and
     * save the score/time in the database
     * @param user logged user's username
     * @param score total time
     */
    public void saveScoreToDb(String user, String score) {
        try {
            connection.setData("insert into time(username,time) value( '" + user + "','" + score + "')");
            System.out.println("Time Saved to DB");
        } catch (Exception ex) {
            Logger.getLogger(SqlFunctions.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    /**
     * Returns the best recorded time of an user
     * @param user username
     * @return Minimum Time
     */
    public static String getMinTime(String user) {

        String minTime = null;
        try {

            ResultSet rs = connection.getData("SELECT MIN(time) AS besttime FROM time WHERE username='" + user + "'");
            while (rs.next()) {
                minTime = rs.getString("besttime");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return minTime;

    }
    
    /**
     *This function gets time ranges from database and returns them in
     * a String Array
     * @return arr[] (String Array)
     */
    public static String[] apidata(){
        
        String[] arr = new String[4];
        
        try {

            ResultSet rs1 = connection.getData("SELECT count(tid) AS tid FROM time WHERE time > '03:00:00'");
            ResultSet rs2 = connection.getData("SELECT count(tid) AS tid FROM time WHERE time between '02:00:00' AND '03:00:00'");
            ResultSet rs3 = connection.getData("SELECT count(tid) AS tid FROM time WHERE time between '01:00:00' AND '02:00:00'");
            ResultSet rs4 = connection.getData("SELECT count(tid) AS tid FROM time WHERE time < '01:00:00'");
            
            while (rs1.next()) {
                arr[0] = rs1.getString("tid");
            }
            while (rs2.next()) {
                arr[1] = rs2.getString("tid");
            }
            while (rs3.next()) {
                arr[2] = rs3.getString("tid");
            }
            while (rs4.next()) {
                arr[3] = rs4.getString("tid");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        return arr;
    
    }

}
