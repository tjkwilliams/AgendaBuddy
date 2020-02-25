package com.example.myapplication.ui.extra;

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

public class ExtraFragment extends Fragment {

    private ExtraViewModel extraViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        extraViewModel =
                ViewModelProviders.of(this).get(ExtraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_extra, container, false);
        final TextView textView = root.findViewById(R.id.text_extra);
        extraViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}