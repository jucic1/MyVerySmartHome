package com.example.myverysmarthome.home;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.alldevices.ListDevicesActivity;
import com.example.myverysmarthome.databinding.ActivityHomeScreenBinding;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.DeviceMenuItem;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeScreenBinding activityHomeBinding;
    HomeViewModel homeViewModel;

    DeviceMenuAdapter deviceMenuAdapter = new DeviceMenuAdapter();
    GroupsAdapter groupsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        RecyclerView recyclerViewDeviceMenu = activityHomeBinding.deviceMenuRecyclerView;

        recyclerViewDeviceMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDeviceMenu.setAdapter(deviceMenuAdapter);

//        deviceMenuAdapter =  new DeviceMenuAdapter(new ItemCallback() {
//            @Override
//            public void onMenuItemClick(DeviceMenuItem item) {
//                Log.d("TEST", "menuitem clicked" + item);
//                startActivity(ListDevicesActivity.getIntent(getApplicationContext(), item));
//            }
//        });


        RecyclerView recyclerViewGroups = activityHomeBinding.groupsRecyclerView;

        groupsAdapter = new GroupsAdapter(new ItemCallback() {
            @Override
            public void onItemClick(ChangeableDeviceItem item) {
                Log.d("TEST", "item clicked" + item);
                startActivity(ChangeDeviceStatusActivity.getIntent(getApplicationContext(), item));
            }
        });
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewGroups.setAdapter(groupsAdapter);

    }
}