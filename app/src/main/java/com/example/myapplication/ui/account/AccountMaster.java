package com.example.myapplication.ui.account;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Will serve as a mock database.
 */
public class AccountMaster {


    public static ArrayList<Account> accountMap;


    public static void initialize(){
        if(accountMap == null){
            accountMap = new ArrayList<>();
            accountMap.add(new Account("admin","admin"));
        }

    }

    public static boolean login(Account a){
        if(a == null) return false;

        for(Account acc : accountMap){
            if(acc.equals(a))
                return true;
        }
        return false;
    }

    public static boolean login(String username, String password){

        Account acc = getAccount(username);

        if(acc != null)
            if(acc.password().equals(password))
                return true;

        return false;
    }

    public static void createAccount(String username, String password){
        accountMap.add(new Account(username, password));
    }

    public static boolean hasAccount(String name){
        return getAccount(name) != null;
    }

    public static boolean hasAccount(Account n){
        return accountMap.contains(n);
    }

    public static Account getAccount(String name){
        for(Account acc : accountMap)
            if(acc.username().equals(name))
                    return acc;
        return null;
    }

}
