package com.example.myverysmarthome.alldevices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.databinding.ActivityListDevicesBinding;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.devices.Device;

import java.util.ArrayList;

public class ListDevicesActivity extends AppCompatActivity {
    final static String MENU_ITEM_KEY = "menu_item_key";

    public static Intent getIntent(Context context, Category item) {
        Intent intent = new Intent(context, ListDevicesActivity.class);
        intent.putExtra(MENU_ITEM_KEY, item);
        return intent;
    }

    ActivityListDevicesBinding activityListDevicesBinding;
    ListDevicesViewModel listDevicesViewModel;

    ListDevicesAdapter listDevicesAdapter;
    Category category;

    @Override
    protected void onResume() {
        super.onResume();
        listDevicesAdapter.setItems(getDevicesForCategory(category));
        listDevicesAdapter.refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListDevicesBinding = ActivityListDevicesBinding.inflate(getLayoutInflater());
        setContentView(activityListDevicesBinding.getRoot());
        listDevicesViewModel = new ViewModelProvider(this).get(ListDevicesViewModel.class);

        RecyclerView recyclerViewAllDevices = activityListDevicesBinding.allDevicesRecyclerView;

        recyclerViewAllDevices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listDevicesAdapter = new ListDevicesAdapter(device -> {
            startActivity(ChangeDeviceStatusActivity.getIntent(getApplicationContext(), device));
        });
        recyclerViewAllDevices.setAdapter(listDevicesAdapter);

        category = (Category) getIntent().getSerializableExtra(MENU_ITEM_KEY);

        activityListDevicesBinding.categoryTextView.setText(category.getTitle());
        listDevicesAdapter.setItems(getDevicesForCategory(category));
    }

    private ArrayList<Device> getDevicesForCategory(Category category) {
        if(category.getDeviceType() == null ){
            return DataContainer.getInstance().getDevices();
        } else {
            return DataContainer.getInstance().getDevicesForCategory(category);
        }
    }
}