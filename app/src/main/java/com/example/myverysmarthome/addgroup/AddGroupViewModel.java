package com.example.myverysmarthome.addgroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.Device;
import com.example.myverysmarthome.model.Group;

import java.util.ArrayList;
import java.util.List;

public class AddGroupViewModel extends ViewModel {

    MutableLiveData<String> groupNameValidation = new MutableLiveData<>();
    MutableLiveData<Boolean> createGroupSuccess = new MutableLiveData<>(false);

    private ArrayList<Device> groupDevices = new ArrayList<>();

    void addDeviceToGroup(Device device){
        groupDevices.add(device);
    }

    void removeDeviceFromGroup(Device device){
        groupDevices.remove(device);
    }

    void createGroup(String name) {
        if(isNameUnique(name) && !groupDevices.isEmpty()) {
            DataContainer.getInstance().createGroup(name, groupDevices);
            createGroupSuccess.setValue(true);
        }else {
            groupNameValidation.setValue("Group name already taken");
            createGroupSuccess.setValue(false);
        }
    }

    private boolean isNameUnique(String name) {
        for(Group group: DataContainer.getInstance().groups) {
            if(group.getTitle().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
