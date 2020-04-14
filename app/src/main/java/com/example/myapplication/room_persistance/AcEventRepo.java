package com.example.myapplication.room_persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplication.customcalendar.Events;

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

    }

    public void updateEvent(Events e){

    }

    public LiveData<List<Events>> retrieveEventsTask() {

        return null;
    }

    public void deleteEvent(Events e){

    }
}
