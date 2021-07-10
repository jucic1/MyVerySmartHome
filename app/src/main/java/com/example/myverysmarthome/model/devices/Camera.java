package com.example.myverysmarthome.model.devices;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Category;

public class Camera extends Device<Boolean>{
    public Camera(String name, String categoryId) {
        super(name, false, categoryId);
    }
}
