package com.example.myverysmarthome.addgroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.Group;

public class AddGroupViewModel extends ViewModel {

    MutableLiveData<String> groupNameValidation = new MutableLiveData<>();
    MutableLiveData<Boolean> createGroup = new MutableLiveData<>(false);

    void createGroup(String name) {
        if(isNameUnique(name)) {
            createGroup.setValue(true);
        }else {
            groupNameValidation.setValue("Group name already taken");
            createGroup.setValue(false);
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
