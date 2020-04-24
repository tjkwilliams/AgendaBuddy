package com.example.myapplication.room_persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplication.async.InsertAsyncTask;
import com.example.myapplication.customcalendar.Events;

import java.util.ArrayList;
import java.util.List;

/*
Responsible for calling methods in the Dao to complete methods
(because they can't be called on the main thread)
@author Ally Lyle
 */
public class AcEventRepo {

    private AcademicEventsDatabase mEDb;

    public AcEventRepo(Context context){
        mEDb = mEDb.getInstance(context); //return singleton of event DB
    }

    public void insertEventTask(Events e){
        new InsertAsyncTask(mEDb.getEventsDao()).execute(e);
    }

    public void updateEvent(Events e){

    }

    /*
    public LiveData<List<Events>> retrieveEventsTask() {
        return mEDb.getEventsDao().getEvents();
    }

     */

    public void retrieveEventsTask(){
       // return mEDb.getEventsDao().getEvents();
    }
    public void deleteEvent(Events e){

    }
}
