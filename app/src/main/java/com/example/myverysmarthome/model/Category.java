package com.example.myverysmarthome.model;

import com.example.myverysmarthome.model.devices.DeviceType;

import java.io.Serializable;
import java.util.UUID;

public class Category implements Serializable {
    private String title;
    private int drawableId;
    private DeviceType deviceType;
    private String uuid;

    public Category(String title, int drawableId, DeviceType deviceType) {
        this.title = title;
        this.drawableId = drawableId;
        this.deviceType = deviceType;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getTitle() {
        return title;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
