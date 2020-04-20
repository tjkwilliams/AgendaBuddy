package com.example.myapplication.connect;

import com.example.myapplication.customcalendar.Events;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GetEventFromDB {
    public static Set<Events> getEventData(int eventId)

    {
        Connection conn = ConnectToDB.getConnection();
        Set<Events> toReturn = new HashSet<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM events");
            while (rs.next()) {
                toReturn.add(new Events());
            }
            conn.close();
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
        }
        return toReturn;
    }
}

