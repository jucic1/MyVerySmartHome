package com.example.myverysmarthome.model;

import java.io.Serializable;

public class DeviceMenuItem implements Serializable {
    public String title;

    public DeviceMenuItem(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
