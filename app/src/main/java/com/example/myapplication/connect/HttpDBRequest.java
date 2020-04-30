package com.example.myapplication.connect;

import com.example.myapplication.customcalendar.Events;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
            return jsonObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        List<Events> events= new ArrayList<>();
        JsonObject json = postRequest("get_community_events.php","");
        JsonArray eventsArray = json.getAsJsonArray();
        Iterator it = eventsArray.iterator();
        JsonObject jObj;
        while (it.hasNext()){
            jObj = (JsonObject) it.next();

            String monthNum = jObj.get("month").getAsString();
            if(Integer.parseInt(monthNum) < 10) {
                monthNum = "0" + monthNum;
            }

            String dayNum = jObj.get("day").getAsString();
            if(Integer.parseInt(dayNum) < 10) {
                dayNum = "0" + dayNum;
            }

            String date = jObj.get("year").getAsString() + "-" + monthNum + "-" + dayNum;
            String month;
            switch (monthNum) {
                case("01"):
                    month = "January";
                    break;
                case("02"):
                    month = "February";
                    break;
                case("03"):
                    month = "March";
                    break;
                case("04"):
                    month = "April";
                    break;
                case("05"):
                    month = "May";
                    break;
                case("06"):
                    month = "June";
                    break;
                case("07"):
                    month = "July";
                    break;
                case("08"):
                    month = "August";
                    break;
                case("09"):
                    month = "September";
                    break;
                case("10"):
                    month = "October";
                    break;
                case("11"):
                    month = "November";
                    break;
                case("12"):
                    month = "December";
                    break;
                default:
                    month = "";
                    break;
            }
            events.add(new Events(jObj.get("title").getAsString(), jObj.get("start_time").getAsString(),
                    jObj.get("end_time").getAsString(), date,
                    month, jObj.get("year").getAsString(), ""));
        }
        return events;
    }
}

