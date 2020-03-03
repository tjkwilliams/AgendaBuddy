package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;
import com.example.myapplication.events.Event;

import java.util.*;

public class MasterCalendar extends AppCompatActivity {

    /*
     * To store a calenderView widget
     */
    private CalendarView calendarView;
    private List<Event> events;
    private TextView myDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_calendar);

        events = new ArrayList<Event>(11);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,01,01);


        //events.add(new EventDay(calendar, R.drawable.ic_launcher_foreground, Color.parseColor("#228B22"))); from internet

        calendarView = findViewById(R.id.calendarView); // get the calendar "object
        //calendarView.setEvents(events); from internet;

        myDate = findViewById(R.id.myDate);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // do something when we select different dates at the calendar
                String date = month + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);

            }
        });

    }


    public boolean createEvent(Event e){
        return events.add(e);
    }
}
