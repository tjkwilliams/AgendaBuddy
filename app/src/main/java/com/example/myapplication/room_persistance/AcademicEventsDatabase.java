package com.example.myapplication.room_persistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myapplication.customcalendar.Events;

/*
    A class to model a SQLite database for academic calendar events
    @author Ally Lyle

 */
//version keeps track of db schema, you should increment every time you change schema
@Database(entities = {Events.class}, version = 1)
public abstract class AcademicEventsDatabase extends RoomDatabase {
    //upon compilation, exports db schema as JSON file

    public static final String DATABASE_NAME = "ac_events_db";

    private static AcademicEventsDatabase instance;

    static AcademicEventsDatabase getInstance(final Context context){
        if(instance==null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AcademicEventsDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract EventsDao getEventsDao();


}
