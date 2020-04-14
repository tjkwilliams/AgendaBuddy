package com.example.myapplication.customcalendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.R;
import com.example.myapplication.room_persistance.AcEventRepo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Page where the calendar is at
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
public class MainActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    private AcEventRepo mRepo;
    private ArrayList<Events> mAcademicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCalendarView = (CustomCalendarView)findViewById(R.id.customCalendarView); // just instantiate customCalendarView

        //connects the calendar activity to the academic event database
        mRepo = new AcEventRepo(this);

/*
        //will put all the academic events in an arraylist
         try {
            customCalendarView.setAllAcademic();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        //customCalendarView.saveEvent("test", "9", "10", "3", "april", "2020", "low", "none", "true");

    }






}
