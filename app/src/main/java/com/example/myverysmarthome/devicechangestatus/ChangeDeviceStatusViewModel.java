package com.example.myverysmarthome.devicechangestatus;

import android.content.SharedPreferences;
import android.telephony.SmsManager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.devices.Device;

public class ChangeDeviceStatusViewModel extends ViewModel {
    MutableLiveData<Boolean> feedback = new MutableLiveData<>();

    public void removeItem(Device device) {
        DataContainer.getInstance().removeDevice(device.getUuid());
    }

    public void sendStatusChange(String deviceName, String status, String type, SharedPreferences sharedPreferences) {
        String message = "[" + deviceName + "][" + status + "][" + type + "]";
        boolean result = sendSMS(sharedPreferences, message);
        feedback.setValue(result);
    }

    public boolean sendSMS(SharedPreferences sharedPreferences, String message) {
        String phoneNumber = sharedPreferences.getString("GSM_MODULE_PHONE_NUMBER", null);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        return true;
    }

}
