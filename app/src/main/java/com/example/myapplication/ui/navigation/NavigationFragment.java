package com.example.myapplication.ui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.ui.account.AccountMaster;
import com.google.android.material.navigation.NavigationView;

public class NavigationFragment extends Fragment implements View.OnClickListener{

    private NavigationViewModel navigationViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        navigationViewModel =
                ViewModelProviders.of(this).get(NavigationViewModel.class);
        View root = inflater.inflate(R.layout.nav_header_side_menu, container, false);
       /* final TextView textView = root.findViewById(R.id.text_calendar);
        navigationViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
        */


        return root;

    }

    @Override
    public void onClick(View v) {
    }
}