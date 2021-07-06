package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;

public class Fan extends Device<Level>{
    public Fan(String name) {
        super(name, Level.ŚREDNI, R.drawable.fan);
    }
}
