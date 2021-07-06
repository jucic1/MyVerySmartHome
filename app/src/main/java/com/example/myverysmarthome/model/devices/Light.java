package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;

public class Light extends Device<Boolean>{
    public Light(String name) {
        super(name, false, R.drawable.lightbulb);
    }
}
