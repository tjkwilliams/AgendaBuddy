package com.example.myapplication.connect;

import com.example.myapplication.customcalendar.Events;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpDBRequest {

    public static void main(String[] args){
        getAllCommunity();
    }

    public static JsonObject postRequest(String php, String post){
        try {
            URL url = new URL("http://18.233.165.117/agenda-buddy/" + php);
            String postData = post;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(postData);
            }

            InputStream inputStream = conn.getInputStream(); //Read from a file, or a HttpRequest, or whatever
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

            JsonArray eventsArray = (JsonArray) jsonObject.get("events");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add event to database
     * I am not sure how we are storing seperate user events
     * @param title
     * @param location
     * @param startTime
     * @param endTime
     * @param year
     * @param month
     * @param day
     */
    public static void addEvent(String title, String location, String startTime, String endTime, String year, String month, String day, String user) {
        postRequest("create_event.php", String.format("title=%s&location=%s&start_time=%s&end_time=%s&year=%s&month=%s&day=%s$user=%s", title, location, endTime, startTime, year, month, day, user));
    }

    public static void addEvent(String title, String location, String startTime, String endTime, String date, String user){
        addEvent(title, location, startTime, endTime, date.substring(0, 4), date.substring(5,7), date.substring(8,10), user);
    }

    public static List<Events> getAllCommunity(){
        postRequest("get_community_events.php","");

    }
}

