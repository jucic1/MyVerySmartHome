package com.example.myverysmarthome.model;

import com.example.myverysmarthome.DataContainer;

import java.util.ArrayList;

public class Group {
    String title;
    ArrayList<String> devicesInGroup;

    public Group(String title) {
        this.title = title;
        this.devicesInGroup = new ArrayList<>();
    }

    public Group(String title, ArrayList<String> devices) {
        this.title = title;
        this.devicesInGroup = devices;
    }

    public void addDevice(String deviceUUID) {
        devicesInGroup.add(deviceUUID);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Device> getDevicesInGroup() {
        ArrayList<Device> devices = new ArrayList<>();
        for(String uuid: devicesInGroup) {
            devices.add(DataContainer.getInstance().getDevice(uuid));
        }
        return devices;
    }
}