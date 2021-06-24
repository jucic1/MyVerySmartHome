package com.example.myverysmarthome.configuredevice;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.databinding.ActivityConfigureDeviceBinding;
import com.example.myverysmarthome.home.HomeActivity;

public class ConfigureDeviceActivity extends AppCompatActivity {

    ActivityConfigureDeviceBinding activityConfigureDeviceBinding;
    ConfigureDeviceViewModel configureDeviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfigureDeviceBinding = ActivityConfigureDeviceBinding.inflate(getLayoutInflater());
        setContentView(activityConfigureDeviceBinding.getRoot());
        configureDeviceViewModel = new ViewModelProvider(this).get(ConfigureDeviceViewModel.class);


        configureDeviceViewModel.deviceNameValidation.observe(this, validationMessage -> {
            activityConfigureDeviceBinding.nameLayout.setError(validationMessage);
        });

        configureDeviceViewModel.configureDevice.observe(this, configureDevice -> {
            if(configureDevice) {
                startActivity(new Intent( ConfigureDeviceActivity.this, HomeActivity.class));
                finish();
            }
        });

        activityConfigureDeviceBinding.addDeviceButton.setOnClickListener(view -> {
            configureDeviceViewModel.configureDevice(activityConfigureDeviceBinding.nameInput.getText().toString());
        });
    }
}
