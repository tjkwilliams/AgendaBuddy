package com.example.myapplication.page;

import java.util.Map;

/*
* A class to model a password database
 */
public class passwordDatabase {
    public Map<String, String> internal;

    public boolean validate(String userName, String password){
        return internal.get(userName).equals(password);
    }
    public void create(String userName, String password){
        internal.put(userName, password);
    }
}

