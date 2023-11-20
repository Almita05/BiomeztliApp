package com.example.biomeztliapp.ui.use;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Precaucion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}