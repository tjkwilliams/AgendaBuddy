package com.example.myapplication.customcalendar;

import android.os.AsyncTask;
import com.androdocs.httprequest.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetWeather extends AsyncTask<String, Void, String> {

    public GetWeather() {

    }

    @Override
    protected String doInBackground(String... strings) {

        String API_KEY = "22679e0129e892d323227914093f8217";
        String LOCATION = "Wheaton,IL";
        String myUrl = "api.openweathermap.org/data/2.5/forecast?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
        /*
        URL url = new URL(myUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        */
        String response = HttpRequest.excuteGet(myUrl);
        return response;
    }

    @Override
    protected void onPostExecute(String result){

        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONObject main = jsonObj.getJSONObject("main");
            JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
            weather.getString("description");
            //temp = main.getString("temp") + "F";
            //weatherDesc = weather.getString("description");

        } catch (JSONException e) {
            //findViewById(R.id.loader).setVisibility(View.GONE);
            //findViewById(R.id.errorText).setVisibility(View.VISIBLE);
        }


    }
}
