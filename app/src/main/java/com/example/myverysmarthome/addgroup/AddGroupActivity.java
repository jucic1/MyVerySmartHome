package com.example.myverysmarthome.addgroup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityCreateGroupBinding;
import com.example.myverysmarthome.home.HomeActivity;
import com.example.myverysmarthome.login.LogInActivity;

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

        addGroupViewModel.groupNameValidation.observe(this, validationMessage -> {
            activityCreateGroupBinding.nameLayout.setError(validationMessage);
        });

        addGroupViewModel.createGroup.observe(this, createGroup -> {
            if(createGroup) {
                startActivity(new Intent( AddGroupActivity.this, HomeActivity.class));
                finish();
            }
        });

        activityCreateGroupBinding.createButton.setOnClickListener(view -> {
            addGroupViewModel.createGroup(activityCreateGroupBinding.nameInput.getText().toString());
        });

    }
}
