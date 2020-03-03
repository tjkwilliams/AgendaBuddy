package com.example.myapplication.events;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event {

    private String name;
    private int priority;
    private String details;
    private Calendar startDate;
    private Calendar endDate;

    public Event(String name, Calendar startDate, Calendar endDate, int priority, String details){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.details = details;
    }

    public void changeStartTime(int year, int month, int day, int hour, int minute){
        startDate.set(year, month, day, hour, minute);
    }

    public void changeEndTime(int year, int month, int day, int hour, int minute){
        endDate.set(year, month, day, hour, minute);
    }

    /**
     * Simply gets the priority of a certain event.
     *
     * @return Priority. 1 = Low, 2 = Medium, 3 = High;
     */
    public int priority(){ return priority; }

    /**
     * Makes an event's priority a string to be able to be displayed.
     *
     * @return The value of an event's priority.
     */
    public String toStringPriority(){
        if(priority == 1)
            return "Low";

        else if(priority == 2)
            return "Medium";

        else if(priority == 3)
            return "High";

        else
            return "Invalid";

    }

    /**
     * Increments priority once if possible.
     */
    public void incPriority(){
        if(priority < 3)
            priority++;
    }

    /**
     * Decrements priority once if possible.
     */
    public void decPriority(){
        if(priority > 1)
            priority--;
    }

    /**
     * Manually sets an event priority.
     *
     * @param p The new prioriry to set the event.
     */
    public void setPriority(int p){
        if(p >= 1 && p <= 3) {
            priority = p;
            System.out.println("Find some way of notifying priority change.");
        }
    }
}
