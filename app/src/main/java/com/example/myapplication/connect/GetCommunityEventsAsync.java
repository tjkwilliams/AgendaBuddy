package com.example.myapplication.connect;

import android.os.AsyncTask;

import com.example.myapplication.customcalendar.Events;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetCommunityEventsAsync extends AsyncTask<String,Void,List<Events>> {


    public AsyncResponse delegate = null;


    /**
     * @param delegate Make sure the Activity implements AsyncResponse, then pass in 'this'
     */
    public GetCommunityEventsAsync(AsyncResponse delegate){
        this.delegate = delegate;
    }



    @Override
    protected List<Events> doInBackground(String... type) {
        try {
            String urlText = "http://18.233.165.117/agenda-buddy/get_community_events.php";
            URL url = new URL(urlText);
            String postData = "type=" + type[0];

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
            JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(inputStream, "UTF-8"));

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            System.out.println(jsonObject.toString());

            JsonArray eventsArray = (JsonArray) jsonObject.getAsJsonArray("events");


            List<Events> events= new ArrayList<>();
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
                        month, jObj.get("year").getAsString(), type[0]));
            }

            return events;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    protected void onPostExecute(List<Events> result) {
        super.onPostExecute(result);
        delegate.processFinish(result);
    }

}
