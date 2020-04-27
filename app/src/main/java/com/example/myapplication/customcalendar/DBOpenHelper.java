package com.example.myapplication.customcalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Part of the open source code
 *
 * The Database itself
 *
 * This is basically where the data all relevant to events is saved to
 * The database is written and readable as you can see from the method names
 *
 * ask me (Brian) for more info and I'll try to explain. I do not fully understand either
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_EVENTS_TABLE = "create table " + DBStructure.EVENT_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBStructure.EVENT + " TEXT, " + DBStructure.START_TIME + " TEXT, " + DBStructure.END_TIME + " TEXT, " + DBStructure.DATE + " TEXT, " + DBStructure.MONTH + " TEXT, "
            + DBStructure.YEAR + " TEXT, "+DBStructure.PRIORITY + " TEXT, " + DBStructure.NOTES + " TEXT, " + DBStructure.Notify + " TEXT)";

    private static final String DROP_EVENTS_TABLE = "DROP TABLE IF EXISTS " + DBStructure.EVENT_TABLE_NAME;



    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        onCreate(db);
    }

    /**
     * Save an event into the database
     * all attributes must be filled, hence why there is a lot of parameters
     *
     * @param event the event object
     * @param startTime the time of event
     * @param endTime
     * @param date the date of event
     * @param month the month event is in
     * @param year the year event is in
     * @param notify boolean value of whether user wants to be notified or not when it is the time of the event
     * @param priority
     * @param notes
     * @param database the SQLite database
     */
    public void SaveEvent(String event, String startTime, String endTime, String date, String month, String year, String priority, String notes, String notify, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.EVENT, event);
        contentValues.put(DBStructure.START_TIME, startTime);
        contentValues.put(DBStructure.END_TIME, endTime);
        contentValues.put(DBStructure.DATE, date);
        contentValues.put(DBStructure.MONTH, month);
        contentValues.put(DBStructure.YEAR, year);
        contentValues.put(DBStructure.PRIORITY, priority);
        contentValues.put(DBStructure.NOTES, notes);
        contentValues.put(DBStructure.Notify, notify);
        database.insert(DBStructure.EVENT_TABLE_NAME, null, contentValues);
    }

    /**
     * Read events info (i think)
     *
     * @param date the date of the event
     * @param database the database
     * @return a query --> the data fetched from the database (im guessing)
     */
    public Cursor readEvents(String date, SQLiteDatabase database) {
        String[] projections = {DBStructure.EVENT, DBStructure.START_TIME, DBStructure.END_TIME, DBStructure.DATE, DBStructure.MONTH, DBStructure.YEAR, DBStructure.PRIORITY, DBStructure.NOTES};
        String selection = DBStructure.DATE + "=?";
        String[] selectionArgs = {date};

        return database.query(DBStructure.EVENT_TABLE_NAME, projections, selection, selectionArgs, null, null, null);
    }

    /**
     * Similar to readEvents (im not quite sure of the difference --> was used for enabling notifications)
     *
     * @param date the date of the event
     * @param event the event
     * @param time the time of the event
     * @param database the database
     * @return a query --> the data fetched from the database
     */
    public Cursor readIDEvents(String date, String event, String time, SQLiteDatabase database) {
        String[] projections = {DBStructure.ID, DBStructure.Notify};
        String selection = DBStructure.DATE + "=? and " + DBStructure.EVENT + "=? and " + DBStructure.START_TIME + "=?";
        String[] selectionArgs = {date, event, time};
        return database.query(DBStructure.EVENT_TABLE_NAME, projections, selection, selectionArgs, null, null, null);
    }

    /**
     *
     * @param month
     * @param year
     * @param database
     * @return
     */
    public Cursor readEventsPerMonth(String month, String year, SQLiteDatabase database) {
        String[] projections = {DBStructure.EVENT, DBStructure.START_TIME, DBStructure.END_TIME, DBStructure.DATE, DBStructure.MONTH, DBStructure.YEAR, DBStructure.PRIORITY, DBStructure.NOTES};
        String selection = DBStructure.MONTH + "=? and " + DBStructure.YEAR + "=?";
        String[] selectionArgs = {month, year};

        return database.query(DBStructure.EVENT_TABLE_NAME, projections, selection, selectionArgs, null, null, null);
    }

    /**
     *
     * @param event
     * @param date
     * @param time
     * @param database
     */
    public void deleteEvent(String event, String date, String time, SQLiteDatabase database) {
        String selection = DBStructure.EVENT + "=? and " + DBStructure.DATE + "=? and " + DBStructure.START_TIME + "=?";
        String[] selectionArg = {event, date, time};
        database.delete(DBStructure.EVENT_TABLE_NAME, selection, selectionArg);
    }

    /**
     *
     * @param date
     * @param event
     * @param time
     * @param notify
     * @param database
     */
    public void updateEventNotification(String date, String event, String time, String notify, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Notify, notify);
        String selection = DBStructure.DATE + "=? and " + DBStructure.EVENT + "=? and " + DBStructure.START_TIME + "=?";
        String[] selectionArgs = {date, event, time};
        database.update(DBStructure.EVENT_TABLE_NAME, contentValues, selection, selectionArgs);

    }

    public void updateEvent(String oldDate, String oldEvent, String oldStartTime, ContentValues values, SQLiteDatabase database) {
        String where = DBStructure.DATE + "=? and " + DBStructure.EVENT + "=? and " + DBStructure.START_TIME + "=?";
        String[] whereArgs = {oldDate, oldEvent, oldStartTime};

        database.update(DBStructure.EVENT_TABLE_NAME, values, where, whereArgs);
    }

}
