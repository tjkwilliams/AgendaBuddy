package com.example.myapplication.page;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.ui.account.Account;
import com.example.myapplication.ui.account.AccountMaster;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 *
 *
 * @author Timothy Williams
 */
public class MainPage extends AppCompatActivity implements View.OnClickListener{

    private int n = 0;

    private TextView username, email;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */

        /*
        NavigationView o = findViewById(R.id.nav_home);
        o.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this, LoginPage.class);
                startActivity(intent);
            }
        });
        */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_calendar, R.id.nav_notifications, R.id.nav_settings,
                R.id.nav_help, R.id.nav_account, R.id.nav_prioritized, R.id.nav_prioritized)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_menu, menu);
        username = (TextView) findViewById(R.id.header_name);
        email = (TextView) findViewById(R.id.email);

        username.setText(AccountMaster.username());
        email.setText(AccountMaster.username() + "@android.com");

        username.setOnClickListener(this);
        email.setOnClickListener(this);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            // Both do nothing. Leave them ALONE.
            case R.id.header_name:

            case R.id.email:

                break;

            default:


        }
    }
}

/*
TextView v = (TextView) findViewById(R.id.accountFragName);

        if (v != null){
            Toast.makeText(this, "Found account label!!!", Toast.LENGTH_SHORT).show();
            account.setView(v);
        } else {
            Toast.makeText(this, "Failed to find account label!!!", Toast.LENGTH_SHORT).show();
        }
 */