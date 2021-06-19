package com.example.myverysmarthome.alldevices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityListDevicesBinding;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.DeviceMenuItem;

public class ListDevicesActivity extends AppCompatActivity {
    final static String MENU_ITEM_KEY = "menu_item_key";

    public static Intent getIntent(Context context, DeviceMenuItem item) {
        Intent intent = new Intent(context, ChangeDeviceStatusActivity.class);
        intent.putExtra(MENU_ITEM_KEY, item);
        return intent;
    }

    ActivityListDevicesBinding activityListDevicesBinding;
    ListDevicesViewModel listDevicesViewModel;

    ListDevicesAdapter listDevicesAdapter = new ListDevicesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListDevicesBinding = ActivityListDevicesBinding.inflate(getLayoutInflater());
        setContentView(activityListDevicesBinding.getRoot());
        listDevicesViewModel = new ViewModelProvider(this).get(ListDevicesViewModel.class);

        RecyclerView recyclerViewAllDevices = activityListDevicesBinding.allDevicesRecyclerView;

        recyclerViewAllDevices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewAllDevices.setAdapter(listDevicesAdapter);
    }
}