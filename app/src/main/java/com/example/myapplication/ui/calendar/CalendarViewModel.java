package com.example.myapplication.ui.calendar;

import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;
import com.example.myapplication.ui.account.AccountMaster;

public class CalendarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private TextView accountLabel;

    public CalendarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where the compacted version on\nthe calendar will go. Basically the view before the master calendar only showing the most com.example.myapplication.events by priority.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}