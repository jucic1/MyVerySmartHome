package com.example.myverysmarthome.model;

import java.io.Serializable;

public class ChangeableDeviceItem implements Serializable {
    public String name;
    public Boolean status;

    public ChangeableDeviceItem(String name) {
        this.name = name;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
