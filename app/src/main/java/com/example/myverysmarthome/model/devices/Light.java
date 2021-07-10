package com.example.myverysmarthome.model.devices;

public class Light extends Device<Boolean> {
    public Light(String name, String categoryId) {
        super(name, false, categoryId);
    }
}
