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
 * @author Yashodha Godage This Class contains all the methods related to
 * SQL/Database
 */
public class SqlFunctions {

    private String username;
    private String name;

    /**
     *
     * @param username
     * @param password Login take two parameters and check them in the database
     * to see the whether the user is already exist in the database
     * @return
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
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Returns the Username of the Logged user
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param uname
     * @param name
     * @param Password Register function takes 3 parameters and create a new
     * user by entering the above mentioned data to the database.
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
            myWriter.write(getUsername());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    
    public String readFromFile() {
        String data = null;
        try {
            File myObj = new File("src\\GameEngine\\user.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Data in file - "+data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }

}
