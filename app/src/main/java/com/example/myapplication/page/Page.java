package com.example.myapplication.page;

import android.view.View;

/**
 *
 *
 * @author Timothy Williams
 */
public abstract class Page {

    /**
     * Not the type of page. But the name that will
     * represent the unique (custom) id of this page.
     */
    private String name;

    // NOTE: Need another instance variable to hold the id of
    // the activity or fragment this page associates with.

    /**
     * Constructor for a page.
     *
     * @param n The unique name of a page.
     */
    public Page(String n){
        name = n;
    }

    /**
     * Gets the unique name of the page.
     *
     * @return This page's unique name.
     */
    public String name(){ return name; }

    /**
     * Come back and fix later to make sure that it can return an activity
     * or a fragment.
     *
     * @return Whichever activity or fragment associated with it.
     */
    public abstract View instance();
}
