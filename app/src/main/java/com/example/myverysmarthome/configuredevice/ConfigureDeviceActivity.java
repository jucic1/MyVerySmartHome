package com.example.myverysmarthome.configuredevice;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.databinding.ActivityConfigureDeviceBinding;
import com.example.myverysmarthome.model.Category;
import com.google.android.material.snackbar.Snackbar;


public class ConfigureDeviceActivity extends AppCompatActivity {

    ActivityConfigureDeviceBinding activityConfigureDeviceBinding;
    ConfigureDeviceViewModel configureDeviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfigureDeviceBinding = ActivityConfigureDeviceBinding.inflate(getLayoutInflater());
        setContentView(activityConfigureDeviceBinding.getRoot());
        configureDeviceViewModel = new ViewModelProvider(this).get(ConfigureDeviceViewModel.class);

        addCategoryRadioButtons();

        configureDeviceViewModel.deviceNameValidation.observe(this, validationMessage -> {
            activityConfigureDeviceBinding.nameLayout.setError(validationMessage);
        });

        configureDeviceViewModel.configureDevice.observe(this, configureDevice -> {
            if(configureDevice) {
                finish();
            }
        });

        configureDeviceViewModel.snackBarMessage.observe(this, snackBarMessage -> {
            Snackbar.make(activityConfigureDeviceBinding.getRoot(),snackBarMessage, Snackbar.LENGTH_LONG).show();
        });

        activityConfigureDeviceBinding.addDeviceButton.setOnClickListener(view -> {
            configureDeviceViewModel.configureDevice(activityConfigureDeviceBinding.nameInput.getText().toString());
        });
    }

    public void addCategoryRadioButtons() {
        for(Category category: DataContainer.getInstance().categories) {
            RadioButton categoryRadioButton = new RadioButton(this);

//            categoryRadioButton.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.lightGray));
            categoryRadioButton.setText(category.getTitle());
            categoryRadioButton.setOnClickListener(view -> {
                if(((RadioButton) view).isChecked()) {
                    configureDeviceViewModel.setCategory(category);
                }
            });

            activityConfigureDeviceBinding.deviceCategoryRadioGroup.addView(categoryRadioButton);
        }
    }
}
