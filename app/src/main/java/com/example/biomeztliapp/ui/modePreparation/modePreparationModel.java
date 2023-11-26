package com.example.biomeztliapp.ui.modePreparation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class modePreparationModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public modePreparationModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Info");
    }

    public LiveData<String> getText() {
        return mText;
    }
}