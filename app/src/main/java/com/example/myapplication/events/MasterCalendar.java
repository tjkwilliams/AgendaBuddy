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

    /*
     * Data Structure to store events
     */
    private List<Event> events;

    /*
     * To connect to button "New Event"
     */
    private TextView myDate;

    /*
     * Current date
     */
    private int currentDay, currentMonth, currentYear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_master_calendar);

        events = new ArrayList<Event>(11);
        currentDay = 1;
        currentMonth = 1;
        currentYear = 2020;

        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth, currentDay);

        calendarView = findViewById(R.id.calendarView); // get the calendar "object"

        myDate = findViewById(R.id.myDateText); // get the button "object"

        calendarView.setOnClickListener(this); // Connect to listener method

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
