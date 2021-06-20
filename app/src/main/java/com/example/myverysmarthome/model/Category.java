package com.example.myverysmarthome.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    String title;
    ArrayList<String> devicesInCategory;

    public Category(String title, ArrayList<String> bedroomDevices) {
        this.title = title;
        this.devicesInCategory = bedroomDevices;
    }

    public Category(String title) {
        this.title = title;
        this.devicesInCategory = new ArrayList<>();
    }

    public void addDevice(String device) {
        devicesInCategory.add(device);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getDevicesInCategory() {
        return devicesInCategory;
    }
}
