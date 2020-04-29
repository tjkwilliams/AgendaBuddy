package com.example.myapplication.customcalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Part of the open source code
 *
 * Toggle notify user or not (i think)
 * Most method are private --> so just helper methods
 *
 * ask me (Brian) for more info and I'll try to explain. I do not fully understand either
 * If your wanna change or have change (i.e commit to gitHub) please tell me so I know (or I guess tell the group as well)
 */
public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Events> arrayList;
    private DBOpenHelper dbOpenHelper;

    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_rowlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Events events = arrayList.get(position);
        String test = events.getEVENT();
        assert test != null;
        holder.Event.setText(events.getEVENT());
        holder.DateTxt.setText(events.getDATE());
        holder.start_TIME.setText(events.getStartTIME());
        holder.end_TIME.setText(events.getEndTIME());
        holder.Priority.setText(events.getPRIORITY());
        holder.Notes.setText(events.getNOTES());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCalendarEvent(events.getEVENT(), events.getDATE(), events.getStartTIME());
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.select.setOnClickListener(new View.OnClickListener() { // edited
            @Override
            public void onClick(View v) {
                if(!arrayList.get(arrayList.size()-1).equals(events)) {
                    arrayList.add(events);
                    Toast.makeText(context, "Event Selected", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });

        /* toggle between 'on' and 'off' notification icon */
        if(isAlarmed(events.getDATE(), events.getEVENT(), events.getStartTIME())) {
            holder.setAlarm.setImageResource(R.drawable.ic_action_notification_on);
        } else {
            holder.setAlarm.setImageResource(R.drawable.ic_action_notification_off);
        }

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(convertStringToDate(events.getDATE()));
        final int alarmYear = dateCalendar.get(Calendar.YEAR);
        final int alarmMonth = dateCalendar.get(Calendar.MONTH);
        final int alarmDay = dateCalendar.get(Calendar.DAY_OF_MONTH);
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(convertStringToTime(events.getStartTIME()));
        final int alarmHour = timeCalendar.get(Calendar.HOUR_OF_DAY);
        final int alarmMinute = timeCalendar.get(Calendar.MINUTE);

        /* Toggling to enable/disable notification for an event requires the program to update status of notifications for that event */
        holder.setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlarmed(events.getDATE(), events.getEVENT(), events.getStartTIME())) {
                    holder.setAlarm.setImageResource(R.drawable.ic_action_notification_off);
                    cancelAlarm(getRequestCode(events.getDATE(), events.getEVENT(), events.getStartTIME()));
                    updateEventNotification(events.getDATE(), events.getEVENT(), events.getStartTIME(), "off");
                    notifyDataSetChanged();
                } else {
                    holder.setAlarm.setImageResource(R.drawable.ic_action_notification_on);
                    Calendar alarmCalendar = Calendar.getInstance();
                    alarmCalendar.set(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute);
                    setAlarm(alarmCalendar, events.getEVENT(), events.getStartTIME(), getRequestCode(events.getDATE(), events.getEVENT(), events.getStartTIME()));
                    updateEventNotification(events.getDATE(), events.getEVENT(), events.getStartTIME(), "on");
                    notifyDataSetChanged();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /**
     * Inner Class
     * Purpose: I am not really sure --> looks just like a struct to me like in C language
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        //TextView DateTxt, Event, start_TIME, end_TIME, Notes, Priority;
        TextView start_TIME, end_TIME, DateTxt, Event, Notes, Priority;
        Button delete, select;
        ImageButton setAlarm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DateTxt = itemView.findViewById(R.id.eventDate);
            Event = itemView.findViewById(R.id.eventname);
            start_TIME = itemView.findViewById(R.id.event_start_time);
            end_TIME = itemView.findViewById(R.id.event_end_time);
            Notes = itemView.findViewById(R.id.event_notes);
            Priority = itemView.findViewById(R.id.event_priority);
            delete = itemView.findViewById(R.id.delete);
            select = itemView.findViewById(R.id.selectEvent);
            setAlarm = itemView.findViewById(R.id.alarmMeBtn);
        }
    }

    /**
     *
     * @param eventDate
     * @return
     */
    private Date convertStringToDate(String eventDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     *
     * @param eventDate
     * @return
     */
    private Date convertStringToTime(String eventDate) {
        SimpleDateFormat format = new SimpleDateFormat("kk:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     *
     * @param event
     * @param date
     * @param time
     */
    private void deleteCalendarEvent(String event, String date, String time) {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.deleteEvent(event, date, time, database);
        dbOpenHelper.close();
    }

    /**
     *
     * @param date
     * @param event
     * @param time
     * @return
     */
    private boolean isAlarmed(String date, String event, String time) {
        boolean alarmed = false;
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readIDEvents(date, event, time, database);
        while(cursor.moveToNext()) {
            String notify = cursor.getString(cursor.getColumnIndex(DBStructure.Notify));
            if(notify.equals("on")) {
                alarmed = true;
            } else {
                alarmed = false;
            }
        }
        cursor.close();
        dbOpenHelper.close();

        return alarmed;
    }

    /**
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
     *
     * @param requestCode
     */
    private void cancelAlarm(int requestCode) {
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    /**
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
     *
     * @param date
     * @param event
     * @param time
     * @param notify
     */
    private void updateEventNotification(String date, String event, String time, String notify) {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.updateEventNotification(date, event, time, notify, database);
        dbOpenHelper.close();
    }



}
