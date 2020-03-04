package com.example.myapplication.page;

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
import com.example.myapplication.ui.account.Account;
import com.example.myapplication.ui.account.AccountMaster;

/**
 * Class for the LoginPage activity that will handle logging in the current
 * user.
 *
 * @author Timothy Williams
 */
public class LoginPage extends AppCompatActivity implements View.OnClickListener{

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
            return;
        } else if(user.length() > 15){
            Toast.makeText(this, "Name can't be longer than 15 characters.", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountMaster.createAccount(user,pass);

        if(AccountMaster.hasAccount(user))
            Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Every time an object from the login conbtent is clicked, depending
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

            case R.id.loginButton:
                loginSuccessful = AccountMaster.login(u, p);

                if (loginSuccessful) {

                    AccountMaster.setDedicatedUser(u);
                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);

                } else {

                    Account acc = AccountMaster.getAccount(u);

                    if (acc == null)

                        Toast.makeText(this, "There isn't an account associated with that username.", Toast.LENGTH_SHORT).show();

                    else {

                        username.startAnimation(shake);
                        password.startAnimation(shake);

                        password.getText().clear();

                    }
                }

                break;

            case R.id.createButton:

                if (!AccountMaster.hasAccount(u))
                    createAccount(u, p);
                else
                    Toast.makeText(this, "Sorry. That username is taken!!", Toast.LENGTH_SHORT).show();

                break;

            case R.id.buddyIcon:
                Toast.makeText(this, "Welcome to Agenda Buddy!!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.pswdTextField:

                if(u.equals("") || u.isEmpty()){
                    Toast.makeText(this, "Must enter username.", Toast.LENGTH_SHORT).show();
                    return;
                } else if(p.equals("") || p.isEmpty()){
                    Toast.makeText(this, "Must enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginSuccessful = AccountMaster.login(u,p);

                if(loginSuccessful){

                    AccountMaster.setDedicatedUser(u);

                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);

                } else {

                    Account acc = AccountMaster.getAccount(u);

                    if(acc == null)
                        Toast.makeText(this, "There isn't an account associated with that username.", Toast.LENGTH_SHORT).show();

                    else if(acc.password().equals(p)) {

                        AccountMaster.setDedicatedUser(u);

                        Intent intent = new Intent(this, MainPage.class);
                        startActivity(intent);

                    } else {

                        username.startAnimation(shake);
                        password.startAnimation(shake);

                        password.getText().clear();
                    }
                }
                break;


            // If any of the following cases were clicked, nothing will happen.
            // THERE AREN'T ANY BREAK STATEMENTS BECAUSE: Since not putting a break statement
            // just executes the following case, then just have them do nothing.
            case R.id.userTextField:

            case R.id.userLabel:

            case R.id.pswdLabel:

            case R.id.tvinfo:

                break;

            default:
                Toast.makeText(this, "Listener not set up yet for this button.", Toast.LENGTH_SHORT).show();

        }
    }
}
