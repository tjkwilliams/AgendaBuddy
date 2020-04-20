package com.example.myapplication.connect;



import java.sql.Connection;
import java.sql.Statement;


public class SendToCommunityDB {

    public void send(String title, String startTime, String endTime, String date) {

        Connection conn;
        conn = ConnectToDB.getConnection();

        try {
            // create a Statement from the connection
            Statement statement = conn.createStatement();

            String query = "INSERT INTO community(title, startTime, endTime, eventDate) " +
                    "VALUES (" + title + "," + startTime + "," + endTime + "," + startTime + ");";
            // insert the data
            statement.executeUpdate(query);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
