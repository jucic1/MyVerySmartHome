package com.example.myverysmarthome.model.devices;

import java.io.Serializable;
import java.util.UUID;

public class Device<T> implements Serializable {
    private String name;
    private String uuid;
    private String categoryId;
    private T value;

    public Device(String name, T value, String categoryId) {
        this.name = name;
        this.value = value;
        this.categoryId = categoryId;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }
}
