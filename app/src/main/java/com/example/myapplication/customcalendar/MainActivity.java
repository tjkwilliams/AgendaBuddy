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
import java.util.List;

/**
 * The Page where the calendar is at
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
public class MainActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    private AcEventRepo mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connects the calendar activity to the academic event database
        mRepo = new AcEventRepo(this);
        try {
            //collect all academic events
            addAllAcEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        customCalendarView = (CustomCalendarView)findViewById(R.id.customCalendarView); // just instantiate customCalendarView
    }

    //get the academic events, it will retrieve a list of all events in academic database
    //since using live data, whenever this is updated it will add the events to the calendar
    private void retrieveAcEvents(){


        /*
        mRepo.retrieveEventsTask().observe(this, new Observer<List<Events>>() {
            @Override
            public void onChanged(List<Events> events) {
                if(events.size()>0){

                }
            }
        });
*/
    }

    /*
    A method to connect to wheaton website and put all academic events on the user's calendar in app
     */
    private void addAllAcEvents() throws IOException {
        Document doc;
        doc = Jsoup.connect("https://25livepub.collegenet.com/calendars/event-collections-general_calendar_wp.rss").get();
        System.out.println(doc.title());

        String day, month, year;
        Elements items = doc.getElementsByTag("item");
        Events n;
        for (Element item : items) {
            Elements t = item.getElementsByTag("title");
            System.out.println(t.text());
            Elements d = item.getElementsByTag("category");
            year = d.text().substring(0, 4);
            month = d.text().substring(5, 7);
            day = d.text().substring(8, 10);

            //a new event representing this data
            n = new Events(t.text(), "", day, month, year);
            //insert each event using background thread
            mRepo.insertEventTask(n);
        }

    }


}
