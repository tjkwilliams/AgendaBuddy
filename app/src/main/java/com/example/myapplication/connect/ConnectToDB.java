package com.example.myapplication.connect;

import java.sql.*;

public class ConnectToDB {
    public static void addToDB () {

        Connection conn;

        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Step 2: Establish the connection to the database
            String url = "jdbc:mariadb://18.233.165.117/test";
            conn = DriverManager.getConnection(url, "root", "my-new-password");

            // create a Statement from the connection
            Statement statement = conn.createStatement();

            // insert the data
            statement.executeUpdate("INSERT INTO testTable " + "VALUES ('josh', 2001, 49)");

        } catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
