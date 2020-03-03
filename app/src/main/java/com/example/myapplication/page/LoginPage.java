package com.example.myapplication.page;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{
    //create instance variables
    private EditText name;
    private EditText password;
    private Button login;
    private passwordDatabase pwd;
    private Button create;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //associate instance variables with the objects on the login page
        name = (EditText)findViewById(R.id.userTextField);
        password = (EditText)findViewById(R.id.pswdTextField);
        login = (Button)findViewById(R.id.loginButton);
        create = (Button)findViewById(R.id.createButton);
        info = (TextView)findViewById(R.id.tvinfo);

        ImageView loginImage = findViewById(R.id.buddyIcon);
        loginImage.setOnClickListener(this);

        login.setOnClickListener(this);
        create.setOnClickListener(this);
        name.setOnClickListener(this);
        password.setOnClickListener(this);
        info.setOnClickListener(this);
        //listener for the buttons
        /*
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });
        create.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                createAccount(name.getText().toString(), password.getText().toString());
            }
        });
        */

    }

    /*
     *   a method to validate login credentials and, if valid, transition to next page
     */
    private boolean validate(String userName, String userPassword){
        return pwd.validate(userName, userPassword);
        /*
        if(pwd.validate(userName, userPassword)==true){
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }else{
            info.setText("incorrect login credentials");
            //print to screen "incorrect login credentials"
        }

        if((userName.equals("admin")) && (userPassword.equals("1234"))){
            //correct login
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }
        */
    }

    /*
     * a method to add account credentials to the login map. Simulates what will happen when we develop a
     * database and server to store accounts
     */
    private void createAccount(String userName, String userPassword){
        pwd.create(userName, userPassword);
        info.setText("now press LOGIN");
        Toast.makeText(this, "DFGJNDGHSDFGHHDFGJHFGDFHDFHFSDHHSDFRFGDSRFGDHGHFDSHDFGDFHGFDGDSHFHDSFGASFDGHSDFHDFHGDFHGSDGFDSFHGHDFJHDFHGHSFDHDGDFHDGHDFHDGHDHSDFHDGHDFHGDFGFDGJHGFDHFDGHFGJFHFDJGFFDJFFDHFDGHYDRFHDTGFDHGFD", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginButton:
                //Toast.makeText(this, "touched login", Toast.LENGTH_SHORT).show();

                if(validate(name.getText().toString(), password.getText().toString())){

                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "incorrect credentials", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.createButton:
                Toast.makeText(this, "touched create", Toast.LENGTH_SHORT).show();

                createAccount(name.getText().toString(), password.getText().toString());
                break;
            case R.id.buddyIcon:
                Toast.makeText(this, "Welcome to Agenda Buddy!!", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();

        }

    }
        /*
        Intent intent = new Intent(MainActivity.this, thirdActivity.class);
        name = (EditText)findViewById(R.id.enterUsername);
        password = (EditText)findViewById(R.id.enterPassword);

        pwd.create(name.getText().toString(), password.getText().toString());
*/


}
