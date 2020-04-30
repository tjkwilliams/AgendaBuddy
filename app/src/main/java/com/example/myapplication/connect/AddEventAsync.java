package com.example.myapplication.connect;

import android.os.AsyncTask;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddEventAsync extends AsyncTask<String, Void, Integer>{

    @Override
    protected Integer doInBackground(String... params) {
        try {
            String urlText = "http://18.233.165.117/agenda-buddy/create_event.php";
            URL url = new URL(urlText);
            String postData = String.format("title=%s&location=%s&start_time=%s&end_time=%s&year=%s&month=%s&day=%s$user=%s",
                    params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7]);


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



            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
