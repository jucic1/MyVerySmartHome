package com.example.myverysmarthome.devicechangestatus;

import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.Device;

public class ChangeDeviceStatusViewModel extends ViewModel {
    public void statusChange(String uuid, boolean status) {
        DataContainer.getInstance().getDevice(uuid).setStatus(status);
    }
}
