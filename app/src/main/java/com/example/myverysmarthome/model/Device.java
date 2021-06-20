package com.example.myverysmarthome.model;

import java.io.Serializable;
import java.util.UUID;

public class Device  implements Serializable {
    public String name;
    public Boolean status;
    public String uuid;

    public Device(String name) {
        this.name = name;
        this.status = false;
        this.uuid = UUID.randomUUID().toString();
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

    public String getUuid() {
        return uuid;
    }
}
