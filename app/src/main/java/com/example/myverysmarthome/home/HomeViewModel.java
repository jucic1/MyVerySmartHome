package com.example.myverysmarthome.home;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;

public class HomeViewModel extends ViewModel {
    MutableLiveData<Boolean> configurationStatus = new MutableLiveData<>(false);
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void configurePhoneNumber(String phoneNumber, SharedPreferences sharedPreferences) {
        if(phoneNumber.matches("[0-9]+") && phoneNumber.length() == 9) {
            sharedPreferences.edit().putString("GSM_MODULE_PHONE_NUMBER", phoneNumber).apply();
            configurationStatus.setValue(true);
        } else {
            errorMessage.setValue("Numer telefonu ma niepoprawny format.");
            configurationStatus.setValue(false);
        }
    }
}
