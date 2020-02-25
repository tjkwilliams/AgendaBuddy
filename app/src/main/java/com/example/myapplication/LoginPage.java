package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Toast.makeText(this,"",Toast.LENGTH_LONG);
                //Toast.makeText(this,"Password entered!!!",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView loginImage = findViewById(R.id.buddyIcon);
        loginImage.setOnClickListener(this);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        /*
        Button loginFragButton = findViewById(R.id.loginFragButton);
        loginFragButton.setOnClickListener(this);
         */
        TextView userTextField = findViewById(R.id.userTextField);
        userTextField.setOnClickListener(this);

        TextView pswdTextField = findViewById(R.id.pswdTextField);
        pswdTextField.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //Intent intent;
        switch(v.getId()){
            case R.id.loginFragButton:
                Intent intent = new Intent(this, LoginPage.class);
                startActivity(intent);
                break;

            case R.id.loginButton:
                //System.out.println("Login in initiated!!!!");
                Toast.makeText(this,"Login initiated!!!",Toast.LENGTH_SHORT).show();
                Intent intentt = new Intent(this, SideMenu.class);
                startActivity(intentt);
                /*
                For later:

                When login is pressed. We need to make that syncing doesn't actually happen unless
                the user wants automatic updates.

                 */
                break;

            case R.id.pswdTextField:
                //System.out.println("Login in initiated!!!!");
                Toast.makeText(this,"Password entered!!!",Toast.LENGTH_SHORT).show();

                break;

            case R.id.userTextField:
                //System.out.println("Login in initiated!!!!");
                Toast.makeText(this,"Username entered!!!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.buddyIcon:
                //System.out.println("Login in initiated!!!!");
                Toast.makeText(this,"Welcome to Agenda Buddy!!!",Toast.LENGTH_SHORT).show();
                break;

             default:
                 System.out.println("Somthing went wrong with one of the listeners.");

        }
    }
}
