package com.example.myapplication.customcalendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

/**
 * The Main Activity page of the app.
 *
 * This page comes after the user logged in
 */
public class MainActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCalendarView = findViewById(R.id.customCalendarView); // instantiate the calendar
    }

}
