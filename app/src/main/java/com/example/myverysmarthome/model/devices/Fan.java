package com.example.myverysmarthome.model.devices;

public class Fan extends Device<Level>{
    public Fan(String name, String categoryId) {
        super(name, Level.MEDIUM, categoryId);
    }
}
