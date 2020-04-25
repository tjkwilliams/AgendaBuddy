package com.example.myapplication.customcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.R;
import com.example.myapplication.room_persistance.AcEventRepo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The Page where the calendar is at
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
public class MainActivity extends AppCompatActivity {

    Button goPriorityButton;
    CustomCalendarView customCalendarView;
    //private AcEventRepo mRepo;
    //private ArrayList<Events> mAcademicEvents;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCalendarView = (CustomCalendarView)findViewById(R.id.customCalendarView); // just instantiate customCalendarView

        goPriorityButton = findViewById(R.id.goPriorityPage);

        goPriorityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent priorityPage = new Intent(getApplicationContext(), PriorityPage.class);
                startActivity(priorityPage);
            }
        });

        //parseXML();

        //connects the calendar activity to the academic event database
       // mRepo = new AcEventRepo(this);

/*
        //will put all the academic events in an arraylist
         try {
            customCalendarView.setAllAcademic();
        } catch (IOException e) {
             Log.d( "111111", "threw exception");
            e.printStackTrace();
        }
*/

        //should save a new event
        customCalendarView.saveEvent("test2", "00:00 AM", "00:00 PM", "2020-04-17", "April", "2020", "low", "none", "off" );

        ArrayList<AcEvent> listApril = new ArrayList<>();
        listApril.add(new AcEvent("Last Day Of Spring Semester Classes", "2020-05-01"));
        listApril.add(new AcEvent("Reading Day - Spring", "2020-05-04"));
        listApril.add(new AcEvent("Spring Final Exams" , "2020-05-04"));
        listApril.add(new AcEvent("Memorial Day - No Classes", "2020-05-25"));

        for(AcEvent e : listApril){
            customCalendarView.saveEvent(e.name, "00:00 AM", "00:00 PM", e.date, "May", "2020", "low", "none", "off");
        }
    }

    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("cal_data.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processParsing(parser);

        } catch (XmlPullParserException e) {

        } catch (IOException e) {
        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<AcEvent> acEvents = new ArrayList<>();
        int eventType = parser.getEventType();
        AcEvent currentEvent = null;
        String d, day, month, year;


        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();

                    if ("player".equals(eltName)) {
                        //currentEvent = new AcEvent();
                        acEvents.add(currentEvent);
                    } else if (currentEvent != null) {
                        if ("title".equals(eltName)) {
                            //currentEvent.setEVENT(parser.nextText());
                            currentEvent.name = parser.nextText();
                        } else if ("category".equals(eltName)) {

                            d = parser.nextText();
                            day = d.substring(8, 10);
                            month = d.substring(5,7);
                            year = d.substring(0,4);

                            currentEvent.date = (year+"-"+month+"-"+day);
                        }
                    }
                    break;
            }

            eventType = parser.next();
        }

        printEvents(acEvents);
    }

    private void printEvents(ArrayList<AcEvent> e) {
        StringBuilder builder = new StringBuilder();

        for (AcEvent ev : e) {
            customCalendarView.saveEvent(ev.name, "00:00 AM", "00:00 PM", ev.date, "April", "2020", "low", "none", "off" );

            builder.append(ev.date).append("\n");
        }

        Log.d(TAG, "HERE IS OUTPUT " +builder.toString());
    }



}
