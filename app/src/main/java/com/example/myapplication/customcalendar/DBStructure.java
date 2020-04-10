package com.example.myapplication.customcalendar;

/**
 * Part of the open source code
 *
 * The Database table (not the database itself)
 *
 * basically the table is called eventsTable
 * all the other attributes are the following: event, time, date, month, year, ID, notify
 *
 * ask me (Brian) for more info and I'll try to explain. I do not fully understand either
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
public class DBStructure {
    public static final String DB_NAME = "EVENTS_DB";
    public static final int DB_VERSION = 2;
    public static final String EVENT_TABLE_NAME = "eventsTable";
    public static final String EVENT = "event";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String ID = "ID";
    public static final String Notify = "notify";
    // add priority here
}
