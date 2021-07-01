package com.example.myverysmarthome.configuredevice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Device;

public class ConfigureDeviceViewModel extends ViewModel {
    MutableLiveData<String> deviceNameValidation = new MutableLiveData<>();
    MutableLiveData<String> snackBarMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> configureDevice = new MutableLiveData<>(false);
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<Category> category = new MutableLiveData<>();

    void configureDevice(String name) {
        if (name.isEmpty() || category.getValue() == null) {
            snackBarMessage.setValue("costam");
        } else {
            if (isNameUnique(name)) {
                Device newDevice = DataContainer.getInstance().createDevice(name);
                DataContainer.getInstance().getCategory(category.getValue().getId()).addDevice(newDevice.getUuid());
                configureDevice.setValue(true);
            } else {
                deviceNameValidation.setValue("Device name already taken");
                configureDevice.setValue(false);
            }
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
