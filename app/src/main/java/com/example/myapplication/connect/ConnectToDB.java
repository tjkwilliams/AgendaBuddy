package com.example.myapplication.connect;

import java.sql.*;

public class ConnectToDB {
    public static Connection getConnection() {

        Connection conn = null;

        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Step 2: Establish the connection to the database
            String url = "jdbc:mariadb://18.233.165.117/test";
            conn = DriverManager.getConnection(url, "root", "my-new-password");

        } catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        return conn;

    }
}
