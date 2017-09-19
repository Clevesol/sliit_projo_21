/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sliit.saloonsayoma.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author handeeban
 */
public class DB {
    
    
    
    /*
    DB Connection details
    
    jdbc:mysql://localhost:3306/db
    */
    
    private final static String HOST = "localhost";
    private final static String PORT = "3306";
    private final static String DB = "mydb";
    private final static String USER = "root";
    private final static String PASSWORD = "@#$%";
    
    
    private static Connection connection;
    
    
    public static  Connection getConnection(){
    
        while(connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                
                String connectionString = "jdbc:mysql://"+ HOST + ":"+PORT+"/" + DB;
                
                //jdbc:mysql://localhost:3306/mydb
                
                
                connection = DriverManager.getConnection(connectionString, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return connection;
    }
    
    
    
    //select * from cars;
    public static ResultSet getResultSet(String query){
    
        try {
            Statement queryStatment = getConnection().createStatement();
            return queryStatment.executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public static boolean executeUpdate(String query){
    
        try {
            return getConnection().createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
}
