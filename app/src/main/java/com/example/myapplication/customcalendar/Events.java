package com.example.myapplication.customcalendar;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

/**
 * Event object
 *
 * The data/info regarding to an instance of an event
 * And getter and setter methods
 */
@Entity
public class Events implements Comparable<Events> {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String EVENT, startTIME, endTIME, DATE, MONTH, YEAR, PRIORITY, NOTES;
    private int priorityInt; // used for sorting
    private boolean alarm;

    /**
     * Main Constructor
     * @param EVENT
     * @param startTIME
     * @param DATE
     * @param MONTH
     * @param YEAR
     * @param PRIORITY
     */
    public Events(String EVENT, String startTIME, String endTIME, String DATE, String MONTH, String YEAR, String PRIORITY, String NOTES) {
        this.EVENT = EVENT;
        this.startTIME = startTIME;
        this.endTIME = endTIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;

        if(PRIORITY.equalsIgnoreCase("high") || PRIORITY.equalsIgnoreCase("h"))
            priorityInt = 3;
        else if(PRIORITY.equalsIgnoreCase("med") || PRIORITY.equalsIgnoreCase("medium") || PRIORITY.equalsIgnoreCase("m"))
            priorityInt = 2;
        else if(PRIORITY.equalsIgnoreCase("low") || PRIORITY.equalsIgnoreCase("l"))
            priorityInt = 1;
        else
            priorityInt = 0;

        this.PRIORITY = PRIORITY;
        this.NOTES = NOTES;
        alarm = false;
    }

    /**
     * Empty constructor to be used for parsing
     */
    public Events(){ }

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

    public String getStartTIME() {
        return startTIME;
    }

    public void setStartTIME(String startTIME) {
        this.startTIME = startTIME;
    }

    public String getEndTIME() {
        return endTIME;
    }

    public void setEndTIME(String endTIME) {
        this.endTIME = endTIME;
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

    public String getPRIORITY() {
        return PRIORITY;
    }

    public void setPRIORITY(String PRIORITY) {
        this.PRIORITY = PRIORITY;
    }

    public String getNOTES() {
        return NOTES;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }

    public int getPriorityInt() { return priorityInt; }

    public void setPriorityInt(int priorityInt) { this.priorityInt = priorityInt; }

    public boolean getAlarm() { return alarm; }

    public void setAlarm(boolean alarm) { this.alarm = alarm; }

    @Override
    public int compareTo(Events event) {
        return priorityInt - event.priorityInt;
    }

}