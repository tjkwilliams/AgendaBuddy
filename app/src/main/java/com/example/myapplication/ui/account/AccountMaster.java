package com.example.myapplication.ui.account;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Will serve as a mock database.
 *
 * @author Timothy Williams
 */
public class AccountMaster {

    /**
     * The base of accounts.
     */
    private static ArrayList<Account> accountMap;

    /**
     * The dedicated user for this session of the app;
     */
    private static Account dedicatedUser;

    /**
     * Initializes the instance of the mock database of accounts.
     */
    public static void initialize(){
        if(accountMap == null){
            accountMap = new ArrayList<>();
            accountMap.add(new Account("admin","admin"));
            accountMap.add(new Account("brian","drown"));
            accountMap.add(new Account("tyler","rios"));
            accountMap.add(new Account("ally","lyle"));
            accountMap.add(new Account("joshua","price"));
            accountMap.add(new Account("tj","williams"));
        }
    }

        /**
     * The details associated with an account.
     *
     * @param a The account to get the details from.
     * @return The details of the account.
     */
    public String accountDetails(Account a){
        if(a == null) return "DNE";

        String ret = "";
        ret += "Name: " + a.username() + "\n";
        ret += "Password: " + a.password();

        return ret;
    }

    /**
     * Sets the user that has loggin in to this app.
     *
     * @param a The user to set.
     */
    public static void setDedicatedUser(Account a){ dedicatedUser = a; }

    public static void setDedicatedUser(String name){
        Account temp = getAccount(name);
        if(temp != null)
            dedicatedUser = temp;
    }

    /**
     * Checks if there is already a user logged in.
     *
     * @return If there is already a dedicated user.
     */
    public static boolean hasDedicatedUser(){ return dedicatedUser != null; }

    /**
     * Removes the dedicated user from the system.
     */
    public static void removeDedicatedUser(){ dedicatedUser = null; }

    /**
     * Gets the username of the dedicated user.
     *
     * @return Dedicated user's username.
     */
    public static String username(){ return dedicatedUser.username(); }

    /**
     * The logging in of an account.
     *
     * @param a The account to login in.
     * @return Whether or not the account can login.
     */
    public static boolean login(Account a){
        if(a == null) return false;

        for(Account acc : accountMap){
            if(acc.equals(a))
                return true;
        }
        return false;
    }

    /**
     * The logging in of an account.
     *
     * @param username The username of the user's account.
     * @param password The password of the user's account.
     * @return Whether or not the account can login.
     */
    public static boolean login(String username, String password){

        Account acc = getAccount(username);

        if(acc != null)
            if(acc.password().equals(password))
                return true;

        return false;
    }

    /**
     * Fetches all of the accounts.
     *
     * @return The list of accounts currently in the database.
     */
    public ArrayList<Account> accounts(){  return accountMap;  }

    /**
     * Fetches the names of all of the accounts' names.
     *
     * @return The list of accounts currently in the database.
     */
    public String accountNames(){
        if(accountMap == null) return "NULL DATABASE";
        if(accountMap.isEmpty()) return "EMPTY";

        String ret = "";

        for(Account account: accountMap){
            ret += accountDetails(account);
        }

        return ret;
    }

    /**
     * Creates an account in the system.
     *
     * @param username The username of the user's account.
     * @param password The password of the user's account.
     */
    public static void createAccount(String username, String password){  accountMap.add(new Account(username, password)); }

    /**
     * Sees if the account exists in the system.
     *
     * @param name The name of the account to look for.
     * @return If the account is registered.
     */
    public static boolean hasAccount(String name){
        return getAccount(name) != null;
    }

    /**
     * Sees if the account exists in the system.
     *
     * @param n The account to look for.
     * @return If the account is registered.
     */
    public static boolean hasAccount(Account n){
        return accountMap.contains(n);
    }

    /**
     * Gets the account associated with this name from the system.
     *
     * @param name Name of the account being searched for.
     * @return The account associated with this account.
     */
    public static Account getAccount(String name){
        for(Account acc : accountMap)
            if(acc.username().equals(name))
                    return acc;
        return null;
    }

}