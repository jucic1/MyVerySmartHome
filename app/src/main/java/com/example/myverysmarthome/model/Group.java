package com.example.myverysmarthome.model;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.model.devices.Device;

import java.util.ArrayList;

public class Group {
    String title;
    ArrayList<String> devicesInGroup;

    public Group(String title, ArrayList<String> devices) {
        this.title = title;
        this.devicesInGroup = devices;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Device> getDevicesInGroup() {
        ArrayList<Device> devices = new ArrayList<>();
        for(String uuid: devicesInGroup) {
            Device device = DataContainer.getInstance().getDevice(uuid);
            if(device != null){
                devices.add(device);
            }
            //add deleting the ids from group
        }
        return devices;
    }
}
