package com.example.myapplication.customcalendar;

import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//WHAT IS THE activity for the main calendar page???
public class Weather extends CustomCalendarView {

    String API_KEY = "22679e0129e892d323227914093f8217";
    String LOCATION = "Wheaton,IL";
    String URL = "api.openweathermap.org/data/2.5/forecast?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";

    String temp = "70 F";
    String weatherDesc = "sunny -";

    public Weather(Context context) {
        super(context);
        new weatherTask().execute();
    }

    public String getTemp(){
        return temp;
    }

    public String getDesc(){
        return weatherDesc;
    }

    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView
    }

    class weatherTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet(URL);
            return response;

        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                temp = main.getString("temp") + "F";
                weatherDesc = weather.getString("description");
                Log.d("MYTAG", "temp: " + temp);
                //then you would just do someView.setText(temp) etc


                /* Views populated, Hiding the loader, Showing the main design */
                //findViewById(R.id.loader).setVisibility(View.GONE);
                //findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);


            } catch (JSONException e) {
                //findViewById(R.id.loader).setVisibility(View.GONE);
                //findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }

        }

    }
}
