package com.example.biomeztliapp.ui.caution;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CautionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CautionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Precaucion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}