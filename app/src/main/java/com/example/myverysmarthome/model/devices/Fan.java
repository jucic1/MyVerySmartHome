package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Category;

public class Fan extends Device<Level>{
    public Fan(String name, String categoryId) {
        super(name, Level.ŚREDNI, categoryId);
    }
}
