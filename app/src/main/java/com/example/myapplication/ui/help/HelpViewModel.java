package com.example.myapplication.ui.help;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 *
 *
 * @author Timothy Williams
 */
public class HelpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This will be where help is shown to user.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}