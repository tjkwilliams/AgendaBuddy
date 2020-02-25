package com.example.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.LoginPage;
import com.example.myapplication.R;
import com.example.myapplication.SideMenu;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(com.example.myapplication.R.layout.fragment_login, container, false);
        final TextView textView = root.findViewById(R.id.text_login);
        loginViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button loginFragButton = (Button) root.findViewById(R.id.loginFragButton);
        String text = "";
        if(loginFragButton != null) {
            loginFragButton.setOnClickListener(this);
            text = "Successfully set Action Listener.";
        } else {
            text = "Action Listener not set.";
        }
        Toast.makeText(this.getContext(),text,Toast.LENGTH_SHORT).show();

        return root;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getContext(), LoginPage.class);
        startActivity(intent);
    }
}