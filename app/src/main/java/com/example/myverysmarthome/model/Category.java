package com.example.myverysmarthome.model;

import com.example.myverysmarthome.DataContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Category implements Serializable {
    String id;
    String title;
    ArrayList<String> devicesInCategory;

    public Category(String title, ArrayList<String> bedroomDevices) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.devicesInCategory = bedroomDevices;
    }

    public Category(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.devicesInCategory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addDevice(String deviceUuid) {
        devicesInCategory.add(deviceUuid);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Device> getDevicesInGroup() {
        ArrayList<Device> devices = new ArrayList<>();
        for(String uuid: devicesInCategory) {
            devices.add(DataContainer.getInstance().getDevice(uuid));
        }
        return devices;
    }
}
