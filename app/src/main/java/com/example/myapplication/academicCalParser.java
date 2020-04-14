package com.example.myapplication;

import com.example.myapplication.customcalendar.Events;
import com.example.myapplication.room_persistance.AcEventRepo;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class academicCalParser {

    public academicCalParser() {
    }

    public static void main(String args[]) throws IOException {
        Document doc;
        doc = Jsoup.connect("https://25livepub.collegenet.com/calendars/event-collections-general_calendar_wp.rss").get();
        System.out.println(doc.title());

        String day, month, year;
        Elements items = doc.getElementsByTag("item");
        for (Element item : items) {
            Elements t = item.getElementsByTag("title");
            System.out.println(t.text());
            Elements d = item.getElementsByTag("category");
            year = d.text().substring(0, 4);
            month = d.text().substring(5, 7);
            day = d.text().substring(8, 10);

            //a new event representing this data
            Events n = new Events(t.text(), "", "", day, month, year, "", "");
            //eventList.add(n);
        }
    }
}
