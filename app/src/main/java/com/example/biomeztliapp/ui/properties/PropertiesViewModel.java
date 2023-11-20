package com.example.biomeztliapp.ui.properties;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PropertiesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PropertiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Propiedades");
    }

    public LiveData<String> getText() {
        return mText;
    }
}