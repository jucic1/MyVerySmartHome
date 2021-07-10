package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Category;

public class Thermostat extends Device<Float> {
    public Thermostat(String name, String categoryId) {
        super(name, 23.0f, categoryId);
    }
}
