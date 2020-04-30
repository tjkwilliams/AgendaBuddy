package com.example.myapplication.connect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDBRequest {

    /**
     * Get an event from the database
     * (not sure how well this will work)
     * @param eid
     */
    public static void getEvent(int eid){
        try {
            URL url = new URL("http://18.233.165.117/agenda-buddy/get_event_details.php");
            String postData = "eid=" + eid;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(postData);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getEventByUID(int uid){
        try {
            URL url = new URL("http://18.233.165.117/agenda-buddy/get_event_details.php");
            String postData = "uid=" + uid;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(postData);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

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
    public static void addEvent(String title, String location, String startTime, String endTime, String year, String month, String day, String user){
        try {
            URL url = new URL("http://18.233.165.117/agenda-buddy/create_event.php");
            String postData = String.format("title=%s&location=%s&start_time=%s&end_time=%s&year=%s&month=%s&day=%s$user=%s", title, location, endTime, startTime, year, month, day, user);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(postData);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEvent(String title, String location, String startTime, String endTime, String date, String user){
        addEvent(title, location, startTime, endTime, date.substring(0, 4), date.substring(5,7), date.substring(8,10), user);
    }

    public static void getAllCommunity(){
        try {
            URL url = new URL("http://18.233.165.117/agenda-buddy/create_event.php");
            String postData = String.format("null");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(postData);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

