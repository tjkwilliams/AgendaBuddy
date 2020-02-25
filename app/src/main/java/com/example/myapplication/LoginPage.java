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
    private EditText name;
    private EditText password;
    private TextView info; //for displaying number incorrect attempts
    private Button login;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name = (EditText)findViewById(R.id.userTextField);
        password = (EditText)findViewById(R.id.pswdTextField);
        info = (TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.loginButton);
        //info.setText("Number of attempts remaining: 5")            ;
        count = 5;
        /*
        Login.setOnClickListener( new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v) {
                                          validate(Name.getText())
                                      }
            });
        */


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                   validate(name.getText().toString(), password.getText().toString(), count);

            }
        });

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

        /*
        ImageView loginImage = findViewById(R.id.buddyIcon);
        loginImage.setOnClickListener(this);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        TextView userTextField = findViewById(R.id.userTextField);
        userTextField.setOnClickListener(this);

        TextView pswdTextField = findViewById(R.id.pswdTextField);
        pswdTextField.setOnClickListener(this);

        
         */
    }

    private void validate(String userName, String userPwd, int count){
        if((userName.equals("Admin")) && (userPwd.equals("1234"))){
            Intent intent = new Intent(this, SideMenu.class);
            startActivity(intent);
        }else{
            count --;
            info.setText("Number of attempts remaining: "+ String.valueOf(count));

            if (count == 0) {
                login.setEnabled(false); //if too many attempts, disable the button
            }
            /*
            else{
                Toast.makeText(this,count + " attempts left.",Toast.LENGTH_SHORT).show();
            }

             */
        }
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
        switch(v.getId()){

            case R.id.loginButton:
                //System.out.println("Login in initiated!!!!");
                Toast.makeText(this,"Login initiated!!!",Toast.LENGTH_SHORT).show();
                // validate(name.getText().toString(), password.getText().toString(), count);

                /*
                For later:

                When login is pressed. We need to make that syncing doesn't actually happen unless
                the user wants automatic updates.

                 */
                break;

            case R.id.pswdTextField:
                Toast.makeText(this,"Password entered!!!",Toast.LENGTH_SHORT).show(); 
                /*
                Toast.makeText(this,"Password entered!!!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SideMenu.class);
                startActivity(intent);
                */

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
