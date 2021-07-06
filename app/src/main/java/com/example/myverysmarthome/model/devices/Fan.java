package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;

public class Fan extends Device<Boolean>{
    public Fan(String name) {
        super(name, false, R.drawable.fan);
    }
}
