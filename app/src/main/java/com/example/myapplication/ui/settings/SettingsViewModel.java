package com.example.myapplication.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 *
 *
 * @author Timothy Williams
 */
public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where the app settings will be.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}