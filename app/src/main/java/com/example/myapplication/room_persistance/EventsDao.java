package com.example.myapplication.room_persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.customcalendar.Events;

import java.util.List;

/*
*   An interface to specific Data Access to Academic Calendar Events
* @author Ally Lyle
 */
@Dao
public interface EventsDao {

    @Insert
    void insertEvents(Events[] events); //to insert a list of events

    @Query("SELECT * FROM academic_events")
    //a query to select all the events from the table academic_events
    //live data will allow you to update in real time, meaning the app will
    //be notified if there is a change, and it will update user's calendar accordingly
    LiveData<List<Events>> getEvents();

    @Delete
    int delete(Events[] events);

    @Update
    int update(Events[] events);


}
