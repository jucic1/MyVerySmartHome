package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Category;

public class Light extends Device<Boolean>{
    public Light(String name,  String categoryId) {
        super(name, false, categoryId);
    }
}
