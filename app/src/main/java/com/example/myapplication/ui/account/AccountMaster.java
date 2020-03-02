package com.example.myapplication.ui.account;

import java.util.HashMap;


/**
 * Will serve as a mock database.
 */
public class AccountMaster {


    public static HashMap<String,Account> accountMap = new HashMap<>();


    public static void initialize(){
        accountMap.put("admin", new Account("admin","admin"));
    }

    public static boolean login(Account a){
        if(a == null) return false;
        String username = a.username();
        if(hasAccount(username)){
            Account account = accountMap.get(username);
            return a.equals(account);
        }
        return false;
    }

    public static boolean login(String username, String password){
        if(hasAccount(username)){
            Account account = accountMap.get(username);
            String acPassword = account.password();
            if(acPassword.equals(password) || acPassword.equals("")){
                return true;
            }
        }
        return false;
    }

    public static void createAccount(String username, String password){
        accountMap.put(username, new Account(username, password));
    }

    public static boolean hasAccount(String name){
        return accountMap.get(name) != null;
    }

    public static boolean hasAccount(Account n){
        return accountMap.get(n.username()) != null;
    }

    public static Account getAccount(String name){
        return accountMap.get(name);
    }

}
