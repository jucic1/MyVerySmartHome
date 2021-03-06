package com.example.myverysmarthome.addgroup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityCreateGroupBinding;
import com.example.myverysmarthome.model.devices.Device;
import com.google.android.material.snackbar.Snackbar;

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
        addGroupAdapter = new AddGroupAdapter(new AddGroupCallBack() {
            @Override
            public void onAddDeviceItem(Device item) {
                addGroupViewModel.addDeviceToGroup(item);
            }

            @Override
            public void onRemoveDeviceItem(Device item) {
                addGroupViewModel.removeDeviceFromGroup(item);
            }

        });
        recyclerViewAllDevices.setAdapter(addGroupAdapter);

        addGroupViewModel.groupNameValidation.observe(this, validationMessage -> {
            Snackbar.make(activityCreateGroupBinding.getRoot(), validationMessage, Snackbar.LENGTH_LONG).show();
        });

        addGroupViewModel.groupNameEmptyValidation.observe(this, validationMessage -> {
            Snackbar.make(activityCreateGroupBinding.getRoot(), validationMessage, Snackbar.LENGTH_LONG).show();
        });

        addGroupViewModel.selectedDevicesValidation.observe(this, errorMessage -> {
            Snackbar.make(activityCreateGroupBinding.getRoot(), errorMessage, Snackbar.LENGTH_LONG).show();
        });

        addGroupViewModel.createGroupSuccess.observe(this, createGroup -> {
            if (createGroup) {
                finish();
            }
        });
        activityCreateGroupBinding.createButton.setOnClickListener(view -> {
            addGroupViewModel.createGroup(activityCreateGroupBinding.nameInput.getText().toString());
        });
    }
}
