package com.example.myapplication.ui.priority;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 *
 *
 * @author Timothy Williams
 */
public class PriorityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PriorityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is an extra fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}