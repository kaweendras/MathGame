/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yashodha Hansimali Godage 
 */

public class connection {
    
  
    
    public static Connection c;

    /**
     *
     * @return connection
     * @throws Exception
     * creates the connection for the MySQL server
     */
    public static Connection getConnection() throws Exception {
        if (c == null) {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mathgame", "root", "");
            System.out.println("Connection Successful"+c);
        }
        return c;
    }

    // Send data TO Database

    /**
     * takes a SQL query to insert or update records
     * @param sql Query
     * @throws Exception
     * 
     */
    public static void setData(String sql) throws Exception {
        connection.getConnection().createStatement().executeUpdate(sql);
    }

    // Get Data From Datavase

    /**
     * takes a SQl query and return records from the database
     * @param sql query
     * @return
     * @throws Exception
     
     */
    public static ResultSet getData(String sql) throws Exception {
        ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
        return rs;
    }
    
   
    
    
    
   
    
}