package com.example.myverysmarthome.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.addgroup.AddGroupActivity;
import com.example.myverysmarthome.alldevices.ListDevicesActivity;
import com.example.myverysmarthome.configuredevice.ConfigureDeviceActivity;
import com.example.myverysmarthome.databinding.ActivityHomeScreenBinding;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.login.LogInActivity;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Device;

public class HomeActivity extends AppCompatActivity implements ConfigureHubDeviceListener {

    ActivityHomeScreenBinding activityHomeBinding;
    HomeViewModel homeViewModel;
    ConfigureHubDialogFragment dialog;
    DeviceMenuAdapter deviceMenuAdapter;
    GroupsAdapter groupsAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        groupsAdapter.refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SOME_KEY", Context.MODE_PRIVATE);
        homeViewModel.configurationStatus.observe(this, configurationStatus -> {
            if(configurationStatus){
                dialog.dismiss();
                sharedPreferences.edit().putBoolean("IS_DEVICE_CONFIGURED",configurationStatus).apply();
            }
        });
        RecyclerView recyclerViewDeviceMenu = activityHomeBinding.deviceMenuRecyclerView;


        if (!sharedPreferences.getBoolean("IS_DEVICE_CONFIGURED", false)) {
            dialog = new ConfigureHubDialogFragment();
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "SOME_TAG");
        }

        deviceMenuAdapter = new DeviceMenuAdapter(new CategoryItemClickListener() {
            @Override
            public void categoryItemCLick(Category deviceMenuItem) {
                startActivity(ListDevicesActivity.getIntent(getApplicationContext(), deviceMenuItem));
            }
        });
        recyclerViewDeviceMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDeviceMenu.setAdapter(deviceMenuAdapter);
        deviceMenuAdapter.setItems(DataContainer.getInstance().categories);

        RecyclerView recyclerViewGroups = activityHomeBinding.groupsRecyclerView;
        groupsAdapter = new GroupsAdapter(new GroupedDeviceCallBack() {
            @Override
            public void onItemClick(Device item) {
                startActivity(ChangeDeviceStatusActivity.getIntent(getApplicationContext(), item));
            }
        });
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewGroups.setAdapter(groupsAdapter);

        activityHomeBinding.addDeviceImage.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, ConfigureDeviceActivity.class));
        });
        activityHomeBinding.addGroupImage.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, AddGroupActivity.class));
        });
    }

    @Override
    public void onConfigure(String mac, String uuid) {
        homeViewModel.configureHub(mac, uuid);
    }
}