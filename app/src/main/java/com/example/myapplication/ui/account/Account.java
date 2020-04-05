package com.example.myapplication.ui.account;

import android.widget.TextView;

import java.util.ArrayList;

import com.example.myapplication.events.Event;

public class Account {

    /**
     * Username of the account.
     */
    private String username, password;

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
        password = "";
        prioritizedEvents = new ArrayList<>(6);
    }

    /**
     * Constructor for the data entity Account.
     *
     * @param u The username of the account.
     */
    public Account(String u){
        username = u;
        password = "";
        prioritizedEvents = new ArrayList<>(6);
    }

    /**
     * Constructor for the data entity Account.
     *
     * @param u The username of the account.
     */
    public Account(String u, String pass){
        username = u;
        password = pass;
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
     * Changes the password of the account.
     *
     * @param s The name to change to.
     */
    public void setPassword(String s){ password = s; }

    /**
     * Getter for account password.
     *
     * @return Password of the account.
     */
    public String password(){ return password; }

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


    public boolean equals(Account other){
        if(this.username.equals(other.username))
            if(this.password.equals(other.password))
                return true;
        return false;
    }

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
