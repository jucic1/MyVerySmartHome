package com.example.myverysmarthome.model.devices;

public class Thermostat extends Device<Float> {
    public Thermostat(String name, String categoryId) {
        super(name, 23.0f, categoryId);
    }
}
