package com.example.myapplication.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.events.CreateEventFragment;

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

    private Button newEvent;

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

        calendarView = (CalendarView) findViewById(R.id.calendarView); // get the calendar "object"

        myDate = (TextView) findViewById(R.id.myDateText); // get the text "object" (might not actually need this)
        newEvent = (Button) findViewById(R.id.newEventBtn); // get the button "object" (might not needed either)

        calendarView.setOnClickListener(this); // Connect to listener method
        newEvent.setOnClickListener(this);

    }


    public boolean createEvent(Event e){
        return events.add(e);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.newEventBtn:
                Intent intent = new Intent(MasterCalendar.this, CreateEventFragment.class);
                startActivity(intent);
                break;

            case R.id.calendarView:
                long date = calendarView.getDate();
                myDate.setText((int) date);
                break;


        }
    }

    public void createEventFragment(View v) {
        Intent intent = new Intent(MasterCalendar.this, CreateEventFragment.class);
        startActivity(intent);
    }
}
