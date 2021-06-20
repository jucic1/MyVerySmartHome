package com.example.myverysmarthome.model;

import java.util.ArrayList;

public class Category {
    String title;
    ArrayList<Device> devicesInCategory;

    public Category(String title) {
        this.title = title;
        this.devicesInCategory = new ArrayList<>();
    }
    public void addDevice(Device device) {
        devicesInCategory.add(device);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Device> getDevicesInCategory() {
        return devicesInCategory;
    }
}
