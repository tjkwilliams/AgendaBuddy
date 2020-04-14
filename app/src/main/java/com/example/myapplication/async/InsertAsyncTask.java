package com.example.myapplication.async;

import android.os.AsyncTask;

import com.example.myapplication.customcalendar.Events;
import com.example.myapplication.room_persistance.EventsDao;

public class InsertAsyncTask extends AsyncTask<Events, Void, Void> {

    private EventsDao mEventsDao;

    public InsertAsyncTask(EventsDao dao) {
        this.mEventsDao = dao;
    }

    @Override
    protected Void doInBackground(Events... events) {
        mEventsDao.insertEvents(events);
        return null;
    }
}
