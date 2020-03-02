package com.example.myapplication.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 *
 *
 * @author Timothy Williams
 */
public class AccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Where the user can decide to change accounts or simply sign out.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}