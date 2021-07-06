package com.example.myverysmarthome.addgroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.Group;

import java.util.ArrayList;

public class AddGroupViewModel extends ViewModel {

    MutableLiveData<String> groupNameValidation = new MutableLiveData<>();
    MutableLiveData<String> selectedDevicesValidation = new MutableLiveData<>();
    MutableLiveData<Boolean> createGroupSuccess = new MutableLiveData<>(false);

    private ArrayList<Device> groupDevices = new ArrayList<>();

    void addDeviceToGroup(Device device) {
        groupDevices.add(device);
    }

    void removeDeviceFromGroup(Device device) {
        groupDevices.remove(device);
    }

    void createGroup(String name) {
        if (isSelected(name)) {
            if (isNameUnique(name)) {
                DataContainer.getInstance().createGroup(name, groupDevices);
                createGroupSuccess.setValue(true);
            } else {
                groupNameValidation.setValue("Już istnieje grupa o tym imieniu");
                createGroupSuccess.setValue(false);
            }
        }
    }

    private boolean isSelected(String name) {
        if (name.isEmpty()) {
            groupNameValidation.setValue("Nazwa grupy nie może być pusta");
            return false;
        }
        if (groupDevices.isEmpty()) {
            selectedDevicesValidation.setValue("Wybierz minimum jedno urządzenie z listy");
            return false;
        }
        return true;
    }

    private boolean isNameUnique(String name) {
        for (Group group : DataContainer.getInstance().groups) {
            if (group.getTitle().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
