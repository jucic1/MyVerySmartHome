package com.example.myverysmarthome.configuredevice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.databinding.ActivityConfigureDeviceBinding;

public class ConfigureDeviceActivity extends AppCompatActivity {

    ActivityConfigureDeviceBinding activityConfigureDeviceBinding;
    ConfigureDeviceViewModel configureDeviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfigureDeviceBinding = ActivityConfigureDeviceBinding.inflate(getLayoutInflater());
        setContentView(activityConfigureDeviceBinding.getRoot());
        configureDeviceViewModel = new ViewModelProvider(this).get(ConfigureDeviceViewModel.class);
    }
}
