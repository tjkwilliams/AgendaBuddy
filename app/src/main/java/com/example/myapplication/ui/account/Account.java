package com.example.myapplication.ui.account;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MasterCalendar;

import java.util.ArrayList;

import com.example.myapplication.R;
import com.example.myapplication.events.Event;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class Account {

    /**
     * Username of the account.
     */
    private String username;

    /**
     * MasterCalendar of account.
     */
    private MasterCalendar masterCalendar;

    /**
     * Label that contains name of the account.
     */
    private TextView accountName;

    /**
     * The list of most important events. It is noteworthy to notice that in
     * the constructor, I limited the size to 6.
     */
    private ArrayList<Event> prioritizedEvents;

    /**
     * Constructor for initial account.
     */
    public Account(){
        username = "";
        masterCalendar = new MasterCalendar();
        prioritizedEvents = new ArrayList<>(6);
    }

    /**
     * Constructor for the data entity Account.
     *
     * @param u The username of the account.
     */
    public Account(String u){
        username = u;
        masterCalendar = new MasterCalendar();
        prioritizedEvents = new ArrayList<>(6);
    }

    /**
     * Changes the name of the account.
     *
     * @param s The name to change to.
     */
    public void setUsername(String s){ username = s; }

    /**
     * Getter for account username.
     *
     * @return Username of the account.
     */
    public String username(){ return username; }

    /**
     * Sets the label that will be connected to the account.
     *
     * @param v The label holding the account username.
     */
    public void setView(TextView v){ accountName = v;  }


    /**
     * Gets the account name label.
     *
     * @return Returns the label with the account name.
     */
    public TextView textView() { return accountName; }

    /**
     * Updates account name text.
     *
     * @param t The updated username of the account to display.
     */
    public void updateText(String t){
        username = t;
        accountName.setText(t);
    }

    /**
     * Will display this account's events from their master calendar
     */
    public void displayMiniCalendarEvents(){
        for(Event event: prioritizedEvents){

            // This is where we display the events from instance "prioritizedEvents"
            // onto MainPage
        }
    }


    /**
     * Will update events by their priority.
     */
    public void updatePrioritizedEvents(){

    }


    /**
     * Will sort events based on priority.
     */
    public void sortPrioritizedEvents(){


    }

    /**
     * Creates an event.
     */
    public void createEvent(){
        Event newEvent;
        // Get stuff from somewhere
        newEvent = null;

        // Put it in event.
        prioritizedEvents.add(newEvent);

        // Sort events by order
        sortPrioritizedEvents();

        //Then update new event.
        updatePrioritizedEvents();
    }

    /**
     * Deletes an event.
     */
    public void deleteEvent(){
        Event newEvent;
        // Get stuff from somewhere
        newEvent = null;

        // Remove event.
        prioritizedEvents.remove(newEvent);

        // Sort events by order
        sortPrioritizedEvents();

        //Then update new event.
        updatePrioritizedEvents();
    }

}
