package com.example.myverysmarthome.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;

public class HomeViewModel extends ViewModel {
    MutableLiveData<Boolean> configurationStatus = new MutableLiveData<>(false);

    public void configureHub(String mac, String hubUuid){
        DataContainer.getInstance().setHubUuid(hubUuid);
        DataContainer.getInstance().setMac(mac);
        configurationStatus.setValue(true);
    }
}
