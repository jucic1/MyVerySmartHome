package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;

public class Thermostat extends Device<Float> {
    public Thermostat(String name) {
        super(name, 23.0f, R.drawable.temperature);
    }
}
