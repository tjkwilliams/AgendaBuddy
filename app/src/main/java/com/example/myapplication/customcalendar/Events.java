package com.example.myapplication.customcalendar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Part of the open source code
 *
 * Event object
 *
 * The data/info regarding to an instance of an event
 * And getter and setter methods
 *
 * (will add 'priority' feature later, or use the Events class made by Joshua or combine)
 *
 * ask me (Brian) for more info and I'll try to explain
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
@Entity(tableName = "academic_events")
public class Events {
    @PrimaryKey(autoGenerate = true) //will automatically generate a new, unique key for each event
    private int id; //will act as primary key in SQLite database

    @ColumnInfo(name = "title")
    private String title;

    String EVENT, TIME, DATE, MONTH, YEAR;

    public Events(String EVENT, String TIME, String DATE, String MONTH, String YEAR) {
        this.EVENT = EVENT;
        this.TIME = TIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
        this.title = EVENT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }
}
