package com.example.myverysmarthome.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.addgroup.AddGroupActivity;
import com.example.myverysmarthome.alldevices.ListDevicesActivity;
import com.example.myverysmarthome.configuredevice.ConfigureDeviceActivity;
import com.example.myverysmarthome.databinding.ActivityHomeScreenBinding;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.devices.Device;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ConfigureHubDeviceListener {

    ActivityHomeScreenBinding activityHomeBinding;
    HomeViewModel homeViewModel;
    ConfigureHubDialogFragment dialog;
    DeviceMenuAdapter deviceMenuAdapter;
    GroupsAdapter groupsAdapter;

    int PERMISSION_REQUEST_CODE = 129436;

    @Override
    protected void onResume() {
        super.onResume();
        groupsAdapter.refresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        ArrayList<Category> categories = DataContainer.getInstance().getCategories();
        ArrayList<Group> groups = DataContainer.getInstance().getGroups();
        ArrayList<Device> devices = DataContainer.getInstance().getDevices();

        String jsonCategories = gson.toJson(categories);
        String jsonGroups = gson.toJson(groups);
        String jsonDevices = gson.toJson(devices);
        writeToFile("categories.txt", jsonCategories);
        writeToFile("devices.txt", jsonDevices);
        writeToFile("groups.txt", jsonGroups);
    }

    public void writeToFile(String filename, String text) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, MODE_PRIVATE); //only our app can access this file
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
            Log.d("permission", "permission denied to SEND_SMS - requesting it");
            String[] permissions = {Manifest.permission.SEND_SMS};
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
        sendSMS("+48699913004", "lol");

        super.onCreate(savedInstanceState);

        activityHomeBinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MY_KEY", Context.MODE_PRIVATE);
        homeViewModel.configurationStatus.observe(this, configurationStatus -> {
            if (configurationStatus) {
                dialog.dismiss();
                sharedPreferences.edit().putBoolean("IS_DEVICE_CONFIGURED", configurationStatus).apply();
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

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            System.out.println();
        } catch (Exception ex) {
            System.out.println("x");
        }
    }

    @Override
    public void onConfigure(String mac, String uuid) {
        homeViewModel.configureHub(mac, uuid);
    }
}