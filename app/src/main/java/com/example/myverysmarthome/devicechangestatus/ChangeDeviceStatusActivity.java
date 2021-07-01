package com.example.myverysmarthome.devicechangestatus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.databinding.ActivityChangeDeviceStatusBinding;
import com.example.myverysmarthome.model.Device;

public class ChangeDeviceStatusActivity extends AppCompatActivity {
    final static String EXTRA_ITEM_KEY = "extra_item_key";

    public static Intent getIntent(Context context, Device item) {
        Intent intent = new Intent(context, ChangeDeviceStatusActivity.class);
        intent.putExtra(EXTRA_ITEM_KEY, item);
        return intent;
    }

    ActivityChangeDeviceStatusBinding activityChangeDeviceStatusBinding;
    ChangeDeviceStatusViewModel changeDeviceStatusViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangeDeviceStatusBinding = ActivityChangeDeviceStatusBinding.inflate(getLayoutInflater());
        setContentView(activityChangeDeviceStatusBinding.getRoot());
        changeDeviceStatusViewModel = new ViewModelProvider(this).get(ChangeDeviceStatusViewModel.class);

        final Device item = (Device) getIntent().getSerializableExtra(EXTRA_ITEM_KEY);

        activityChangeDeviceStatusBinding.deviceNameText.setText(item.getName());
        activityChangeDeviceStatusBinding.switch1.setChecked(item.getStatus());

        activityChangeDeviceStatusBinding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                changeDeviceStatusViewModel.statusChange(item.getUuid(), status);
            }
        });

        activityChangeDeviceStatusBinding.forgetDevice.setOnClickListener(view -> {
            changeDeviceStatusViewModel.removeItem(item);
            finish();
        });
    }

}