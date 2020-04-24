package com.example.myapplication.ui.account;

import android.widget.TextView;

import java.util.ArrayList;

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
     * Constructor for initial account.
     */
    public Account(){
        username = "";
        password = "";
    }

    /**
     * Constructor for the data entity Account.
     *
     * @param u The username of the account.
     */
    public Account(String u){
        username = u;
        password = "";
    }

    /**
     * Constructor for the data entity Account.
     *
     * @param u The username of the account.
     */
    public Account(String u, String pass){
        username = u;
        password = pass;
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

}
