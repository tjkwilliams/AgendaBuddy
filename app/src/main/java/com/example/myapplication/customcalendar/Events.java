package com.example.myapplication.customcalendar;

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
public class Events {
    String EVENT, startTIME, endTIME, DATE, MONTH, YEAR, PRIORITY, NOTES;

    /**
     * Old constructor
     * @param EVENT
     * @param startTIME
     * @param DATE
     * @param MONTH
     * @param YEAR
     */
    public Events(String EVENT, String startTIME, String DATE, String MONTH, String YEAR) {
        this.EVENT = EVENT;
        this.startTIME = startTIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
        PRIORITY = "N/A";
        NOTES = "";
    }

    /**
     * New Constructor
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
        this.PRIORITY = PRIORITY;
        this.NOTES = NOTES;
    }

    public Events(String EVENT, String startTIME, String endTIME, String DATE, String MONTH, String YEAR) {
        this.EVENT = EVENT;
        this.startTIME = startTIME;
        this.endTIME = endTIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
        this.PRIORITY = "N/A";
        this.NOTES = "";
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
}