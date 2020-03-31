package com.example.myapplication.page;

import java.util.HashMap;

/**
 *
 *
 * @author Timothy Williams
 */
public class PageMaster {

    /**
     * A map of page names to pages.
     */
    private static HashMap<String, Page> pages;


    /**
     * Constructor for a PageMaster.
     */
    public PageMaster(){
        pages = new HashMap<>();
    }

    /**
     * Adds page to the collection of app pages!
     *
     * @param p Page to add.
     */
    public static void addPage(Page p){  pages.put(p.name(),p); }

    /**
     * Adds a page with a specific name.
     *
     * @param s Specific name.
     * @param p Page to add.
     */
    public static void addPage(String s, Page p){  pages.put(s,p);  }

}
