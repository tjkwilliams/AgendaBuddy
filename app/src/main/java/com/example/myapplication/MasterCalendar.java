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

import com.applandeo.materialcalendarview.EventDay;
import com.example.myapplication.events.Event;

import java.util.*;

public class MasterCalendar extends AppCompatActivity {

    /*
     * To store a calenderView widget
     */
    private CalendarView calendarView;
    List<Event> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_calendar);

        events = new ArrayList<Event>();

        Calendar calendar = Calendar.getInstance();
        // use local Event class made by @Joshua to create new event

        //events.add(new EventDay(calendar, R.drawable.ic_launcher_foreground, Color.parseColor("#228B22"))); from internet

        calendarView = findViewById(R.id.calendarView); // get the calendar "object
        //calendarView.setEvents(events); from internet

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // do something when we select different dates at the calendar
            }
        });

    }


    protected boolean createEvent(Event e){
        return events.add(e);
    }
}
