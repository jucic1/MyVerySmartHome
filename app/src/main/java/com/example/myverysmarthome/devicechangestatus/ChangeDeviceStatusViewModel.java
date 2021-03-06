package com.example.myverysmarthome.devicechangestatus;

import android.content.SharedPreferences;
import android.telephony.SmsManager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.devices.Device;

public class ChangeDeviceStatusViewModel extends ViewModel {
    MutableLiveData<String> feedback = new MutableLiveData<>();

    public void removeItem(Device device) {
        DataContainer.getInstance().removeDevice(device.getUuid());
    }

    public void sendStatusChange(String deviceName, String method, String value, SharedPreferences sharedPreferences) {
        String message = "[" + deviceName + "][" + method + "][" + value + "]";
        String phoneNumber = sharedPreferences.getString("GSM_MODULE_PHONE_NUMBER", null);
        if(sendSMS(phoneNumber, message)){
            feedback.setValue("Wiadomość o treści \"" + message + "\" została wysłana na numer " + phoneNumber);
        }else {
            feedback.setValue("Wysłanie wiadomości się nie powiodło");
        }
    }

    public boolean sendSMS(String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        return true;
    }

}
