package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Category;

public class Plug extends Device<Boolean>{
    public Plug(String name, String categoryId) {
        super(name, false, categoryId);
    }
}
