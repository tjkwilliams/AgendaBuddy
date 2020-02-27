package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

public class MasterCalendar extends AppCompatActivity {

    /*
     * To store a calenderView widget
     */
    private CalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_calendar);

        calendarView = findViewById(R.id.calendarView); // get the calendar "object"

    }
}
