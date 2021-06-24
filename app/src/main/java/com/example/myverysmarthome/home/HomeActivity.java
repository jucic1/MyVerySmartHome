package com.example.myverysmarthome.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.addgroup.AddGroupActivity;
import com.example.myverysmarthome.alldevices.ListDevicesActivity;
import com.example.myverysmarthome.databinding.ActivityHomeScreenBinding;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.login.LogInActivity;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Device;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeScreenBinding activityHomeBinding;
    HomeViewModel homeViewModel;

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
        RecyclerView recyclerViewDeviceMenu = activityHomeBinding.deviceMenuRecyclerView;

        deviceMenuAdapter =  new DeviceMenuAdapter(new CategoryItemClickListener() {
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

        });
        activityHomeBinding.addGroupImage.setOnClickListener(view -> {
            startActivity(new Intent( HomeActivity.this, AddGroupActivity.class));
        });
        activityHomeBinding.logOutTextView.setOnClickListener(view -> {
            startActivity(new Intent( HomeActivity.this, LogInActivity.class));
        });

    }
}