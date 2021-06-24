package com.example.myverysmarthome.addgroup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityCreateGroupBinding;
import com.example.myverysmarthome.databinding.ActivityHomeScreenBinding;
import com.example.myverysmarthome.databinding.ActivityLogInBinding;
import com.example.myverysmarthome.home.DeviceMenuAdapter;
import com.example.myverysmarthome.home.GroupsAdapter;
import com.example.myverysmarthome.home.HomeViewModel;
import com.example.myverysmarthome.login.LogInViewModel;

public class AddGroupActivity extends AppCompatActivity {
    ActivityCreateGroupBinding activityCreateGroupBinding;
    AddGroupViewModel addGroupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateGroupBinding = ActivityCreateGroupBinding.inflate(getLayoutInflater());
        setContentView(activityCreateGroupBinding.getRoot());

        addGroupViewModel = new ViewModelProvider(this).get(AddGroupViewModel.class);
    }
}
