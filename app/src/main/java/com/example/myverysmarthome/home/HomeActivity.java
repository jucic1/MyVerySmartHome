package com.example.myverysmarthome.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityHomeScreenBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeScreenBinding activityHomeBinding;
    HomeViewModel homeViewModel;

    DeviceMenuAdapter deviceMenuAdapter = new DeviceMenuAdapter();
    GroupsAdapter groupsAdapter = new GroupsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        RecyclerView recyclerViewDeviceMenu = activityHomeBinding.deviceMenuRecyclerView;

        recyclerViewDeviceMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDeviceMenu.setAdapter(deviceMenuAdapter);

        RecyclerView recyclerViewGroups = activityHomeBinding.groupsRecyclerView;

        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewGroups.setAdapter(groupsAdapter);

    }
}