package com.example.myapplication.ui.account;

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

import com.example.myapplication.page.LoginPage;
import com.example.myapplication.R;


/**
 *
 *
 * @author Timothy Williams
 */
public class AccountFragment extends Fragment implements View.OnClickListener{

    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        final TextView textView = root.findViewById(R.id.text_account);
        accountViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button loginFragButton = (Button) root.findViewById(R.id.accountNameButton);
        loginFragButton.setOnClickListener(this);

        //TextView view = (TextView)findViewById(R.id.accountFragName);

        return root;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getContext(), LoginPage.class);
        startActivity(intent);
        AccountMaster.removeDedicatedUser();
        Toast.makeText(this.getContext(),"Returned back to login!",Toast.LENGTH_SHORT).show();
    }
}