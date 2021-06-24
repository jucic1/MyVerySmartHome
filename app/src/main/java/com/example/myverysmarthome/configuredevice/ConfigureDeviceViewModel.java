package com.example.myverysmarthome.configuredevice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.Device;

public class ConfigureDeviceViewModel extends ViewModel {
    MutableLiveData<String> deviceNameValidation = new MutableLiveData<>();
    MutableLiveData<Boolean> configureDevice = new MutableLiveData<>(false);

    void configureDevice(String name) {
        if(isNameUnique(name)) {
            configureDevice.setValue(true);
        }else {
            deviceNameValidation.setValue("Device name already taken");
            configureDevice.setValue(false);
        }
    }

    private boolean isNameUnique(String name) {
        for(Device device: DataContainer.getInstance().devices) {
            if(device.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
