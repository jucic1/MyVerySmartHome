package com.example.myverysmarthome.model;

import java.util.ArrayList;

public class Group {
    String title;
    ArrayList<Device> devicesInGroup;

    public Group(String title) {
        this.title = title;
        this.devicesInGroup = new ArrayList<>();
    }
    public void addDevice(Device device) {
        devicesInGroup.add(device);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Device> getDevicesInGroup() {
        return devicesInGroup;
    }
}
