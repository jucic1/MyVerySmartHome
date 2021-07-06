package com.example.myverysmarthome.configuredevice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.DeviceType;

public class ConfigureDeviceViewModel extends ViewModel {
    MutableLiveData<String> deviceNameValidation = new MutableLiveData<>();
    MutableLiveData<String> snackBarMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> configureDevice = new MutableLiveData<>(false);
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<Category> category = new MutableLiveData<>();

    void configureDevice(String name) {
        if (name.isEmpty()){
            snackBarMessage.setValue("Nazwa urządzenia nie może być pusta");
        } else if (category.getValue() == null) {
            snackBarMessage.setValue("Urządzenie musi zostać przypisane do kategorii");
        } else {
            if (isNameUnique(name)) {
                Device newDevice = DataContainer.getInstance().createDevice(name, fromCategory(category.getValue().getTitle()));
                DataContainer.getInstance().getCategory(category.getValue().getId()).addDevice(newDevice.getUuid());
                configureDevice.setValue(true);
            } else {
                deviceNameValidation.setValue("Urządzenie o takiej nazwie już istnieje");
                configureDevice.setValue(false);
            }
        }
    }

    private DeviceType fromCategory(String value){
        switch (value) {
            case "Swiatło":
                return DeviceType.LIGHT;
            case "Termostat":
                return DeviceType.THERMOSTAT;
            case "Włącznik":
                return DeviceType.PLUG;
            case "Kamera":
                return DeviceType.CAMERA;
            case "Wiatrak":
                return DeviceType.FAN;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setCategory(Category category) {
        this.category.setValue(category);
    }

    private boolean isNameUnique(String name) {
        for (Device device : DataContainer.getInstance().devices) {
            if (device.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
