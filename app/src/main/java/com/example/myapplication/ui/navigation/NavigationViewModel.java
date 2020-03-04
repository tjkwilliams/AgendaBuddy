package com.example.myapplication.ui.navigation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.navigation.NavigationView;

public class NavigationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NavigationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where the compacted version on\nthe calendar will go. Basically the view before the master calendar only showing the most com.example.myapplication.events by priority.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
