package com.example.myverysmarthome.devicechangestatus;

import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.devices.Device;

public class ChangeDeviceStatusViewModel extends ViewModel {
//    public void statusChange(String uuid, T status) {
//        DataContainer.getInstance().getDevice(uuid).setValue(status);
//    }

    public void removeItem(Device device) {
        DataContainer.getInstance().removeDevice(device.getUuid());
    }
}
