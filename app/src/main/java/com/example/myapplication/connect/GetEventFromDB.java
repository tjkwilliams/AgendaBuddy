package com.example.myapplication.connect;

import com.example.myapplication.customcalendar.Events;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


public class GetEventFromDB {
    public static Set<Events> getEventData(int eventId)

    {
        Connection conn = DBConnect.getConnection();
        Set<Events> toReturn = new HashSet<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM events");
            while (rs.next()) {
                Date date = rs.getDate("eventDate");

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);


                toReturn.add(new Events(rs.getString("title"),
                        rs.getTime("startTime").toString(), rs.getTime("endTime").toString(),
                        String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), String.valueOf(calendar.get(Calendar.MONTH)),
                        String.valueOf(calendar.get(Calendar.YEAR)), "", ""));
            }
            conn.close();
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
        }
        return toReturn;
    }
}

