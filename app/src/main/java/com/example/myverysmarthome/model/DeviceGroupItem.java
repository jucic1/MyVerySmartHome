package com.example.myverysmarthome.model;

import java.util.ArrayList;

public class DeviceGroupItem {
    String title;
    ArrayList<ChangeableDeviceItem> devicesInGroup;

    public DeviceGroupItem(String title, ArrayList<ChangeableDeviceItem> devicesInGroup) {
        this.title = title;
        this.devicesInGroup = devicesInGroup;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<ChangeableDeviceItem> getDevicesInGroup() {
        return devicesInGroup;
    }
}
