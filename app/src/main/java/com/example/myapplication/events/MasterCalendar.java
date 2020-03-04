package com.example.myapplication.events;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.*;

public class MasterCalendar extends AppCompatActivity implements View.OnClickListener{

    /*
     * To store a calenderView widget
     */
    private CalendarView calendarView;
    private List<Event> events;
    private TextView myDate;
    private int currentDay, currentMonth, currentYear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_calendar);

        events = new ArrayList<Event>(11);
        currentDay = 01;
        currentMonth = 01;
        currentYear = 2020;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,01,01);


        //events.add(new EventDay(calendar, R.drawable.ic_launcher_foreground, Color.parseColor("#228B22"))); from internet

        calendarView = findViewById(R.id.calendarView); // get the calendar "object
        //calendarView.setEvents(events); from internet;

        myDate = findViewById(R.id.myDateText);

        calendarView.setOnClickListener(this);

/*
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // do something when we select different dates at the calendar
                String date = month + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);

               // myDate.getText().
                currentDay = dayOfMonth;
                currentMonth = month;
                currentYear = year;
            }
        });

 */

    }


    public boolean createEvent(Event e){
        return events.add(e);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.myDateText:

                break;

            case R.id.calendarView:
                long date = calendarView.getDate();
                myDate.setText("What in teh");
                break;



        }
    }
}
