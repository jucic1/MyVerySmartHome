package com.example.myverysmarthome.addgroup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityCreateGroupBinding;

public class AddGroupActivity extends AppCompatActivity {
    ActivityCreateGroupBinding activityCreateGroupBinding;
    AddGroupViewModel addGroupViewModel;

    AddGroupAdapter addGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateGroupBinding = ActivityCreateGroupBinding.inflate(getLayoutInflater());
        setContentView(activityCreateGroupBinding.getRoot());

        addGroupViewModel = new ViewModelProvider(this).get(AddGroupViewModel.class);

        RecyclerView recyclerViewAllDevices = activityCreateGroupBinding.listDevicesRecyclerView;

        recyclerViewAllDevices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        addGroupAdapter = new AddGroupAdapter();
        recyclerViewAllDevices.setAdapter(addGroupAdapter);
    }
}
