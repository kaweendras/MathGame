/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import GameEngine.SqlFunctions;
import java.io.IOException;

/**
 *
 * @author Kaweendra
 */
public class test {
    
    public static void main(String[] args) throws IOException {
        SqlFunctions S1 = new SqlFunctions();
        S1.setUsername("SK");
        S1.saveUserToFile();
    }
    
}
