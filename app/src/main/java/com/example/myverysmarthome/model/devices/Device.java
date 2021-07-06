package com.example.myverysmarthome.model.devices;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.UUID;

public class Device<T> implements Serializable {
    private String name;
    private String uuid;
    private T value;
    int drawableId;

    public Device(String name, T value, int drawableId) {
        this.name = name;
        this.value = value;
        this.drawableId = drawableId;
        this.uuid = UUID.randomUUID().toString();
    }

    public int getDrawableId() {
        return drawableId;
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
