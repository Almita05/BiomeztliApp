package com.example.biomeztliapp.ui.ingredients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IngredientsModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IngredientsModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Info");
    }

    public LiveData<String> getText() {
        return mText;
    }
}