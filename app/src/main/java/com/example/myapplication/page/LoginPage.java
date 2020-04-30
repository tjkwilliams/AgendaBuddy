package com.example.myapplication.page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.customcalendar.MainActivity;
import com.example.myapplication.ui.account.Account;
import com.example.myapplication.ui.account.AccountMaster;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for the LoginPage activity that will handle logging in the current
 * user.
 *
 * @author Timothy Williams
 */
public class LoginPage extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    /**
     * Username - The textfield that contains the username of the account that the user wants
     * to login to.
     * Password - The textfield that contains the password of the account that the user wants
     * to login to.
     */
    private EditText username, password;

    /**
     * LoginButton - Button that attempts a login for a user based on the credentials entered
     * in the username and password text fields.
     * CreateAccountButton - Button that creates an account for a user based on the credentials
     * entered in the username and password text fields.
     */
    private Button loginButton, createAccountButton;

    /**
     * Info - Text that describes the create account button.
     */
    private TextView info;

    /**
     * BuddyIcon - The icon that displays on the login screen.
     */
    private ImageView buddyIcon;

    /**
     * Shake - Animation for when a user enters in incorrect credentials.
     */
    private Animation shake;

    /**
     * TotalAttempts - The total number of login attempts.
     * ConsecutiveFailedAttempts - The number of consecutive failed attempts.
     * MaxFailedAttempts - The maximum amount of failed attempts a person has on a username.
     */
    private int totalAttempts, consecutiveFailedAttempts, maxFailedAttempts;

    /**
     * AttemptLog - The log of all successful attempts.
     * Failed - The log of all unsuccessful attempts.
     */
    private HashMap<String, Integer> succeeded, failed;

    /**
     * List of accounts that are locked due to too any failed attempts.
     */
    private ArrayList<String> locked;

    /**
     * Google sign in object.
     */
    private GoogleSignInOptions signInOptions;

    private GoogleApiClient gapi;
    private static final int SIGN_IN=1;
    private SignInButton googleSignInButton;

    /**
     * Basically a constructor for an activity.
     *
     * @param savedInstanceState The apps instance of this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        AccountMaster.initialize();

        //associate instance variables with the objects on the login page
        username = (EditText)findViewById(R.id.userTextField);
        password = (EditText)findViewById(R.id.pswdTextField);
        loginButton = (Button)findViewById(R.id.loginButton);
        createAccountButton = (Button)findViewById(R.id.createButton);
        info = (TextView)findViewById(R.id.tvinfo);
        buddyIcon = (ImageView)findViewById(R.id.buddyIcon);

        loginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
        username.setOnClickListener(this);
        password.setOnClickListener(this);
        info.setOnClickListener(this);
        buddyIcon.setOnClickListener(this);

        shake = AnimationUtils.loadAnimation(this, R.anim.shake);

        totalAttempts = 0;
        consecutiveFailedAttempts = 0;
        maxFailedAttempts = 3;

        succeeded = new HashMap<>();
        failed = new HashMap<>();
        locked = new ArrayList<>();

        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        gapi = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        googleSignInButton = findViewById(R.id.googleSignInButton);
        googleSignInButton.setOnClickListener(this);

    }

    /**
     * Adds a user that successfully logged on to the system.
     *
     * @param user The most recent user to log on.
     */
    public void log(String user){
        if(!succeeded.containsKey(user))
            succeeded.put(user,1);
        else
            succeeded.put(user, succeeded.get(user) + 1);
    }

    /**
     * Adds a user that unsuccessfully logged on to the system.
     *
     * @param user The most recent user to fail log on.
     */
    public void failedLog(String user){
        if(!failed.containsKey(user))
            failed.put(user,1);
        else
            failed.put(user, failed.get(user) + 1);
    }

    /**
     * Creates an account from the login page.
     *
     * @param user Username associated with the account.
     * @param pass Password associated with the account.
     */
    public void createAccount(String user, String pass){

        Account a = AccountMaster.getAccount(user);

        if(a != null){
            Toast.makeText(this, "Account already exist!", Toast.LENGTH_SHORT).show();
            username.startAnimation(shake);
            return;
        } else if(user.length() > 15){
            Toast.makeText(this, "Name can't be longer than 15 characters.", Toast.LENGTH_SHORT).show();
            username.startAnimation(shake);
            return;
        } else if(pass.isEmpty() || pass.equals("")){
            Toast.makeText(this, "Your account needs a password!", Toast.LENGTH_SHORT).show();
            password.startAnimation(shake);
            return;
        }

        AccountMaster.createAccount(user,pass);

        if(AccountMaster.hasAccount(user))
            Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Every time an object from the login content is clicked, depending
     * on which was clicked, one of the following cases will execute.
     *
     * @param v The object clicked.
     */
    @Override
    public void onClick(View v) {

        // Gets the text from the username and password textfields.
        String u = username.getText().toString(), p = password.getText().toString();
        Account a;
        boolean loginSuccessful;

        switch(v.getId()) {

            case R.id.googleSignInButton:



                Intent googleIntent = Auth.GoogleSignInApi.getSignInIntent(gapi);
                startActivityForResult(googleIntent,SIGN_IN);

                break;

            case R.id.loginButton:

                if(consecutiveFailedAttempts > 8) {
                    Toast.makeText(this, "LOL YOU FORGOT YOUR PASSWORD????? SO DUMB!\nNO MORE ATTEMPTS ALLOWED.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(locked.contains(u)){
                    Toast.makeText(this, "Locked from \"" + u + "\".", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginSuccessful = AccountMaster.login(u, p);

               if (loginSuccessful) {

                    AccountMaster.setDedicatedUser(u);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    consecutiveFailedAttempts = 0;

                    log(u);

                } else {

                    Account acc = AccountMaster.getAccount(u);

                    if (acc == null) {

                        if(u.isEmpty() || u.equals("")) {
                            Toast.makeText(this, "Must enter username.", Toast.LENGTH_SHORT).show();
                            username.startAnimation(shake);
                        }

                    } else if(p.equals("") || p.isEmpty()){
                        Toast.makeText(this, "Must enter password.", Toast.LENGTH_SHORT).show();
                        password.startAnimation(shake);
                        return;
                    } else {

                        failedLog(u);

                        if(AccountMaster.hasAccount(u) && failed.get(u) != null && failed.get(u) > maxFailedAttempts) {
                            Toast.makeText(this, "Sorry, there have been too many tries for this account. You are now locked from \"" + u + "\".", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        username.startAnimation(shake);
                        password.startAnimation(shake);

                        consecutiveFailedAttempts++;
                    }

                    password.getText().clear();
                }

                totalAttempts++;

                break;

            case R.id.createButton:

                if (!AccountMaster.hasAccount(u))
                    createAccount(u, p);
                else {
                    Toast.makeText(this, "Sorry. That username is taken!!", Toast.LENGTH_SHORT).show();
                    username.startAnimation(shake);
                }

                break;

            case R.id.buddyIcon:
                Toast.makeText(this, "Welcome to Agenda Buddy!!", Toast.LENGTH_SHORT).show();
                buddyIcon.startAnimation(shake);
                break;

            case R.id.pswdTextField:

                if(consecutiveFailedAttempts > 8) {
                    Toast.makeText(this, "LOL YOU FORGOT YOUR PASSWORD????? SO DUMB!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(locked.contains(u)){
                    Toast.makeText(this, "Locked from \"" + u + "\".", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(u.equals("") || u.isEmpty()){
                    Toast.makeText(this, "Must enter username.", Toast.LENGTH_SHORT).show();
                    username.startAnimation(shake);
                    return;
                } else if(p.equals("") || p.isEmpty()){
                    Toast.makeText(this, "Must enter password.", Toast.LENGTH_SHORT).show();
                    password.startAnimation(shake);
                    return;
                }

                loginSuccessful = AccountMaster.login(u,p);

                if(loginSuccessful){

                    if(locked.contains(u)){
                        Toast.makeText(this, "Locked from \"" + u + "\".", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    AccountMaster.setDedicatedUser(u);

                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);

                    consecutiveFailedAttempts = 0;

                    log(u);

                } else {

                    Account acc = AccountMaster.getAccount(u);

                    if(acc == null) {
                        Toast.makeText(this, "Couldn't find that account.", Toast.LENGTH_SHORT).show();

                        username.startAnimation(shake);

                        // Just a sure check just in case.
                    } else if(p.equals("") || p.isEmpty()){
                        Toast.makeText(this, "Must enter password.", Toast.LENGTH_SHORT).show();
                        password.startAnimation(shake);
                        return;
                    } else if(acc.password().equals(p)) {

                        AccountMaster.setDedicatedUser(u);

                        Intent intent = new Intent(this, MainPage.class);
                        startActivity(intent);

                        consecutiveFailedAttempts = 0;

                        log(u);

                    } else {

                        failedLog(u);

                        if(AccountMaster.hasAccount(u) && failed.get(u) != null && failed.get(u) > maxFailedAttempts) {
                            Toast.makeText(this, "Sorry, there have been too many tries for this account. You are now locked from \"" + u + "\".", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        username.startAnimation(shake);
                        password.startAnimation(shake);

                        password.getText().clear();
                        consecutiveFailedAttempts++;
                    }
                }

                totalAttempts++;

                break;

            case R.id.tvinfo:

                createAccountButton.startAnimation(shake);

                break;

            // If any of the following cases were clicked, nothing will happen.
            // THERE AREN'T ANY BREAK STATEMENTS BECAUSE: Since not putting a break statement
            // just executes the following case, then just have them do nothing.
            case R.id.userTextField:

            case R.id.userLabel:

            case R.id.pswdLabel:

                break;

            default:
                Toast.makeText(this, "Listener not set up yet for this button.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //super.onActivityReenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN){
            GoogleSignInResult results = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(results.isSuccess()){
                Toast.makeText(this,results.toString(), Toast.LENGTH_SHORT).show();


                startActivity(new Intent(LoginPage.this, MainActivity.class));



            } else {
                //Toast.makeText(this,"Google Login Failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
