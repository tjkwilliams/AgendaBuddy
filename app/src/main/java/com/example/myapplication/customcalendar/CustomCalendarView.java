package com.example.myapplication.customcalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.connect.HttpDBRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * The Master Calendar
 *
 * This class handles all user interaction/inputs in the main activity page where the calendar is located at
 */
public class CustomCalendarView extends LinearLayout {

    Button priorityLow, priorityHigh, updateEvent, syncButton;
    CheckBox checkbox_email, checkbox_ath, checkbox_ac;
    ImageButton nextButton, previousButton;
    TextView currentDate;
    GridView gridView;

    private static final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();
    Events eventToUpdate;
    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute;

    /* How this works (i think or at least how I understand it so far):
     * Basically SQLite has a local database and this instance variable acts sort of like a 'pointer'
     * To access the SQLite database, this variable is instantiated with a reference to the local files of the database
     * Then we do whatever we need to do while we have that reference (read/write to the database)
     * Then once we are done accessing it or we are done in a method, we close the connection to the database
     */
    DBOpenHelper dbOpenHelper;

    /**
     * Constructor
     * @param context
     */
    public CustomCalendarView(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Main Constructor
     * @param context
     * @param attrs
     */
    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitializeLayout();
        SetUpCalendar();

        /* sync data upon user request*/
        syncButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //something that connects to the server
                if(checkbox_email.isChecked()){
                    Toast.makeText(context.getApplicationContext(),"email checked",Toast. LENGTH_SHORT);

                }
                if(checkbox_email.isChecked()){

                }
                if(checkbox_email.isChecked()){

                }

                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        SetUpCalendar();
                    }
                });

            }
        });

        /* Shows a display of a a list of events for current month sorted by highest priority */
        priorityHigh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(context).inflate(R.layout.show_events_layout, null);
                RecyclerView recyclerView = showView.findViewById(R.id.eventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                Collections.sort(eventsList);
                Collections.reverse(eventsList);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(), (ArrayList<Events>) eventsList);
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();

                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        SetUpCalendar();
                    }
                });

            }
        });

        /* Shows a display of a a list of events for current month sorted by lowest priority */
        priorityLow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(context).inflate(R.layout.show_events_layout, null);
                RecyclerView recyclerView = showView.findViewById(R.id.eventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                Collections.sort(eventsList);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(), (ArrayList<Events>) eventsList);
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();

                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        SetUpCalendar();
                    }
                });

            }
        });

        /* change the month display backwards (ex: from March to February */
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                SetUpCalendar();
            }
        });

        /* change the month display forwards (ex: from August to September */
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                SetUpCalendar();
            }
        });

        /* Tapping a day on the calendar will invoke the 'addEvent' prompt/option */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                final View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout, null);
                final EditText eventName = addView.findViewById(R.id.eventName);

                final TextView eventStartTime = addView.findViewById(R.id.eventStartTime);
                ImageButton setStartTime = addView.findViewById(R.id.setEventStartTime);
                final CheckBox alarmMe = addView.findViewById(R.id.alarmMe);

                final TextView eventEndTime = addView.findViewById(R.id.eventEndTime);
                ImageButton setEndTime = addView.findViewById(R.id.setEventEndTime);
                final EditText eventPriority = addView.findViewById(R.id.eventPriority);

                final EditText eventNotes = addView.findViewById(R.id.eventNotes);

                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.setTime(dates.get(position));
                alarmYear = dateCalendar.get(Calendar.YEAR);
                alarmMonth = dateCalendar.get(Calendar.MONTH);
                alarmDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

                Button addEvent = addView.findViewById(R.id.addEvent);

                /* When user taps the time icon to set the start time of the event, a time picker widget will be shown */
                setStartTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hFormat = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                                String event_Time = hFormat.format(c.getTime());
                                eventStartTime.setText(event_Time);
                                alarmHour = c.get(Calendar.HOUR_OF_DAY);
                                alarmMinute = c.get(Calendar.MINUTE);

                            }
                        }, hours, minutes, false);
                        timePickerDialog.show();
                    }
                });

                /* When user taps the time icon to set the end time of the event, a time picker widget will be shown */
                setEndTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hFormat = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                                String event_Time = hFormat.format(c.getTime());
                                eventEndTime.setText(event_Time);

                            }
                        }, hours, minutes, false);
                        timePickerDialog.show();
                    }
                });

                final String date = eventDateFormat.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));

                /* things related to when you press the addEvent button */
                addEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /* check if the 'notify me' checkbox is checked or not --> basically check if user wants to be notified or not */
                        if(alarmMe.isChecked()) {
                            saveEvent(eventName.getText().toString(), eventStartTime.getText().toString(), eventEndTime.getText().toString(), date, month, year, eventPriority.getText().toString(), eventNotes.getText().toString(), "on");
                            Toast.makeText(context, "Event Saved", Toast.LENGTH_SHORT).show();
                            SetUpCalendar();
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute);
                            setAlarm(calendar, eventName.getText().toString(), eventStartTime.getText().toString(), getRequestCode(date
                                , eventName.getText().toString(), eventStartTime.getText().toString()));
                            alertDialog.dismiss();
                        } else {
                            saveEvent(eventName.getText().toString(), eventStartTime.getText().toString(), eventEndTime.getText().toString(), date, month, year, eventPriority.getText().toString(), eventNotes.getText().toString(), "off");
                            Toast.makeText(context, "Event Saved", Toast.LENGTH_SHORT).show();
                            SetUpCalendar();
                            alertDialog.dismiss();
                        }

                    }
                });

                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

        /* press and hold on a day in the calendar will show a list of events that are set on that day */
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String date = eventDateFormat.format(dates.get(position));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
                RecyclerView recyclerView = showView.findViewById(R.id.eventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(), CollectEventByDate(date));
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();

                /* This means when you press outside of the 'prompt' box */
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        SetUpCalendar();
                    }
                });

                return true;
            }
        });

        /* when user press the update event button, it replicates the process of adding an event but instead of making a new event it updates the old one */
        updateEvent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                eventToUpdate = eventsList.get(eventsList.size()-1);
                if(eventToUpdate == null)
                    Toast.makeText(context, "Please Select an Event to Update", Toast.LENGTH_SHORT).show();
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    final View addView = LayoutInflater.from(context).inflate(R.layout.add_newevent_layout, null);
                    final EditText eventName = addView.findViewById(R.id.eventName);

                    final TextView eventStartTime = addView.findViewById(R.id.eventStartTime);
                    ImageButton setStartTime = addView.findViewById(R.id.setEventStartTime);
                    final CheckBox alarmMe = addView.findViewById(R.id.alarmMe);

                    final TextView eventEndTime = addView.findViewById(R.id.eventEndTime);
                    ImageButton setEndTime = addView.findViewById(R.id.setEventEndTime);
                    final EditText eventPriority = addView.findViewById(R.id.eventPriority);

                    final EditText eventNotes = addView.findViewById(R.id.eventNotes);

                    eventName.setText(eventToUpdate.getEVENT());
                    eventStartTime.setText(eventToUpdate.getStartTIME());

                    if(eventToUpdate.getAlarm())
                        alarmMe.setChecked(true);

                    eventEndTime.setText(eventToUpdate.getEndTIME());
                    eventPriority.setText(eventToUpdate.getPRIORITY());
                    eventNotes.setText(eventToUpdate.getNOTES());

                    Calendar dateCalendar = Calendar.getInstance();
                    String temp = eventToUpdate.getDATE();
                    temp = temp.substring(temp.length()-2);
                    dateCalendar.setTime(dates.get(Integer.parseInt(temp)));
                    alarmYear = dateCalendar.get(Calendar.YEAR);
                    alarmMonth = dateCalendar.get(Calendar.MONTH);
                    alarmDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

                    Button addEvent = addView.findViewById(R.id.addEvent);

                    /* When user taps the time icon to set the start time of the event, a time picker widget will be shown */
                    setStartTime.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar calendar = Calendar.getInstance();
                            int hours = calendar.get(Calendar.HOUR_OF_DAY);
                            int minutes = calendar.get(Calendar.MINUTE);

                            TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    c.setTimeZone(TimeZone.getDefault());
                                    SimpleDateFormat hFormat = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                                    String event_Time = hFormat.format(c.getTime());
                                    eventStartTime.setText(event_Time);
                                    alarmHour = c.get(Calendar.HOUR_OF_DAY);
                                    alarmMinute = c.get(Calendar.MINUTE);

                                }
                            }, hours, minutes, false);
                            timePickerDialog.show();
                        }
                    });

                    /* When user taps the time icon to set the end time of the event, a time picker widget will be shown */
                    setEndTime.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar calendar = Calendar.getInstance();
                            int hours = calendar.get(Calendar.HOUR_OF_DAY);
                            int minutes = calendar.get(Calendar.MINUTE);

                            TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    Calendar c = Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    c.set(Calendar.MINUTE, minute);
                                    c.setTimeZone(TimeZone.getDefault());
                                    SimpleDateFormat hFormat = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                                    String event_Time = hFormat.format(c.getTime());
                                    eventEndTime.setText(event_Time);

                                }
                            }, hours, minutes, false);
                            timePickerDialog.show();
                        }
                    });

                    final String date = eventToUpdate.getDATE();
                    final String month = eventToUpdate.getMONTH();
                    final String year = eventToUpdate.getYEAR();

                    /* things related to when you press the addEvent button */
                    addEvent.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            /* check if the 'notify me' checkbox is checked or not --> basically check if user wants to be notified or not */
                            if(alarmMe.isChecked()) {
                                ContentValues values = getUpdateValues(eventName.getText().toString(), eventStartTime.getText().toString(), eventEndTime.getText().toString(), date, month, year, eventPriority.getText().toString(), eventNotes.getText().toString(), "on");
                                updateEvent(eventToUpdate, values);
                                Toast.makeText(context, "Event Updated", Toast.LENGTH_SHORT).show();
                                eventsList.remove(eventToUpdate);
                                SetUpCalendar();
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute);
                                setAlarm(calendar, eventName.getText().toString(), eventStartTime.getText().toString(), getRequestCode(date
                                        , eventName.getText().toString(), eventStartTime.getText().toString()));
                                alertDialog.dismiss();
                            } else {
                                ContentValues values = getUpdateValues(eventName.getText().toString(), eventStartTime.getText().toString(), eventEndTime.getText().toString(), date, month, year, eventPriority.getText().toString(), eventNotes.getText().toString(), "on");
                                updateEvent(eventToUpdate, values);
                                Toast.makeText(context, "Event Updated", Toast.LENGTH_SHORT).show();
                                eventsList.remove(eventToUpdate);
                                SetUpCalendar();
                                alertDialog.dismiss();
                            }

                        }
                    });

                    builder.setView(addView);
                    alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

    }

    /**
     * Something to do with setting up notifications
     *
     * @param date
     * @param event
     * @param time
     * @return
     */
    private int getRequestCode(String date, String event, String time) {
        int code = 0;
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readIDEvents(date, event, time, database);
        while(cursor.moveToNext()) {
            code = cursor.getInt(cursor.getColumnIndex(DBStructure.ID));
        }
        cursor.close();
        dbOpenHelper.close();

        return code;
    }

    /**
     * Something to do to set up the alarm/notification of an event if the user wants to be notified
     *
     * @param calendar
     * @param event
     * @param time
     * @param requestCode
     */
    private void setAlarm(Calendar calendar, String event, String time, int requestCode) {
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("event", event);
        intent.putExtra("time", time);
        intent.putExtra("id", requestCode);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    /**
     * Fetch events by a certain date from database and save that to an ArrayList data structure
     *
     * @param date
     * @return
     */
    private ArrayList<Events> CollectEventByDate(String date) {
        ArrayList<Events> arrayList = new ArrayList<>();
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readEvents(date, database);
        while(cursor.moveToNext()) {
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String startTime = cursor.getString(cursor.getColumnIndex(DBStructure.START_TIME));
            String endTime = cursor.getString(cursor.getColumnIndex(DBStructure.END_TIME));
            String Date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            String priority = cursor.getString(cursor.getColumnIndex(DBStructure.PRIORITY));
            String notes = cursor.getString(cursor.getColumnIndex(DBStructure.NOTES));
            Events events = new Events(event, startTime, endTime, Date, month, year, priority, notes);
            arrayList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();

        return arrayList;
    }

    /**
    * Helper method to Save event to SQLite database
     * It calls SaveEvent(...) method in DBOpenHelper.java
     *
     * @param event
     * @param startTime
     * @param endTime
     * @param date
     * @param month
     * @param year
     * @param notify
     * @param priority
     * @param notes
     */
    public void saveEvent(String event, String startTime, String endTime, String date, String month, String year, String priority, String notes, String notify) {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event, startTime, endTime, date, month, year, priority, notes, notify, database);
        dbOpenHelper.close();

        HttpDBRequest.addEvent(event, "", startTime, endTime, year, month, date);
    }

    /**
     * Helper method to update event in SQLite database
     * it calls updateEvent(...) method in DBOpenHelper.java
     * @param eventUpdateRef
     * @param values
     */
    public void updateEvent(Events eventUpdateRef, ContentValues values) {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        String oldDate = eventUpdateRef.getDATE();
        String oldEvent = eventUpdateRef.getEVENT();
        String oldStartTime = eventUpdateRef.getStartTIME();
        dbOpenHelper.updateEvent(oldDate, oldEvent, oldStartTime, values, database);
        dbOpenHelper.close();
    }

    /**
     * Helper method for updateEvent method above
     * It packages all the new info of the event in a ContentValues object
     * @param event
     * @param startTime
     * @param endTime
     * @param date
     * @param month
     * @param year
     * @param priority
     * @param notes
     * @param notify
     * @return
     */
    private ContentValues getUpdateValues(String event, String startTime, String endTime, String date, String month, String year, String priority, String notes, String notify) {
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
        return contentValues;
    }

    /*takes a list of all academic events and sets them in the calendar*/
    /*
    public void setAllAcademic() throws IOException {
       // ArrayList<Events> e = addAllAcEvents();
        for(int i=0; i<e.size(); i++){
            Events n = e.get(i);
            saveEvent(n.getEVENT(), "00:00 AM", "00:00 PM", n.getDATE(), n.getMONTH(), n.getYEAR(), "low", "none", "off" );
        }
    }
    */
/*
    public void getAcEvents(){
        File f = cal_data.xml;

    }

 */

    /*
        A method to connect to wheaton website and put all academic events on the user's calendar in app
         */
    /*

    private ArrayList<Events> addAllAcEvents() throws IOException {
        Document doc;
        doc = Jsoup.connection("https://25livepub.collegenet.com/calendars/event-collections-general_calendar_wp.rss").bufferUp();
        System.out.println(doc.title());


        //will hold all the new events
        ArrayList<Events> academicEvents = new ArrayList<Events>();

        String day, month, year, date;
        Elements items = doc.getElementsByTag("item");
        Events n;
        for (Element item : items) {
            Elements t = item.getElementsByTag("title");
            System.out.println(t.text());
            Elements d = item.getElementsByTag("category");
            year = d.text().substring(0, 4);
            month = d.text().substring(5, 7);
            day = d.text().substring(8, 10);
            date = year + "-" + month +"-" +day;

            academicEvents.add(new Events(t.text(), "", "", date, "April", year, "low", "none"));

            //a new event representing this data
            //n = new Events(t.text(), "", day, month, year);
            //insert each event using background thread
            //mRepo.insertEventTask(n);
            //customCalendarView.saveEvent(t.text(), "", "", day, month, year, "", "" ,"");
        }
        return academicEvents;
    }

    */
    /**
     * Initialize all the position and references of the different things
     * associated with the calendar in the Main Activity page
     */
    private void InitializeLayout() {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout, this);
        nextButton = view.findViewById(R.id.nextBtn);
        previousButton = view.findViewById(R.id.previousBtn);
        currentDate = view.findViewById(R.id.currentDate);
        gridView = view.findViewById(R.id.gridView);
        priorityHigh = view.findViewById(R.id.priorityHigh);
        priorityLow = view.findViewById(R.id.priorityLow);
        updateEvent = view.findViewById(R.id.updateButton);
        syncButton = view.findViewById(R.id.syncButton);
        checkbox_email = view.findViewById(R.id.checkbox_email);
        checkbox_ath = view.findViewById(R.id.checkbox_ath);
        checkbox_ac  = view.findViewById(R.id.checkbox_ac);
        eventToUpdate = null;
    }

    /**
     * Sets, updates, and display the gridView of the calendar
     * Every time this method is called, it updates the events in the current month
     * by fetching data from SQLite database.
     */
    private void SetUpCalendar() {
        String dateCurrent = dateFormat.format(calendar.getTime());
        currentDate.setText(dateCurrent);

        /* fills in the days of the month in the gridView */
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth);
        collectEventsPerMonth(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));

        while(dates.size() < MAX_CALENDAR_DAYS) {
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        myGridAdapter = new MyGridAdapter(context, dates, calendar, eventsList);
        gridView.setAdapter(myGridAdapter);

    }

    /**
     * Get all the events on the month we are in
     * and save that in the ArrayList instance variable 'eventsList'
     *
     * @param Month the month displayed on the calendar
     * @param Year the year displayed on the calendar
     */
    private void collectEventsPerMonth(String Month, String Year) {
        eventsList.clear();
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readEventsPerMonth(Month, Year, database);
        while(cursor.moveToNext()) {
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String startTime = cursor.getString(cursor.getColumnIndex(DBStructure.START_TIME));
            String endTime = cursor.getString(cursor.getColumnIndex(DBStructure.END_TIME));
            String date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            String priority = cursor.getString(cursor.getColumnIndex(DBStructure.PRIORITY));
            String notes = cursor.getString(cursor.getColumnIndex(DBStructure.NOTES));
            Events events = new Events(event, startTime, endTime, date, month, year, priority, notes);
            eventsList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
    }

}