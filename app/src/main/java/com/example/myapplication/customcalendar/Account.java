package com.example.myapplication.customcalendar;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.example.myapplication.page.LoginPage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Account {

    /**
     * Username - Chosen username.
     * GivenName - Actual name.
     * PersonalFamilyName - The Full Name of this user.
     * Email - Current user's email.
     * Id - This person's id.
     */
    public static String username, personGivenName, personFamilyName, email, personId;
    private static Uri personPhoto;
    private static GoogleSignInAccount acct;
    private static DBOpenHelper dbOpenHelper;
    GoogleSignInAccount account;

    public static void initializeAccount(Activity activity, Context context){

        dbOpenHelper = new DBOpenHelper(context);

        acct = GoogleSignIn.getLastSignedInAccount(activity);
        if (acct != null) {
            username = acct.getDisplayName();
            personGivenName = acct.getGivenName();
            personFamilyName = acct.getFamilyName();
            email = acct.getEmail();
            personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            // This is where the account will either be initialized in our DB
            // or where information is grabbed from DB.
        }
    }

    public static boolean isActive(){ return acct != null; }


    public static boolean hasFamName(){ return personFamilyName != null && !personFamilyName.isEmpty(); }

    public static boolean hasName(){ return personGivenName != null && !personGivenName.isEmpty(); }

    public static boolean hasEmail(){ return email != null && !email.isEmpty(); }

    public static boolean hasUsername(){ return username != null && !username.isEmpty(); }


    public static String famName(){ return personFamilyName; }

    public static String name(){ return personGivenName; }

    public static String email(){ return email; }

    public static String username(){ return username; }
}
