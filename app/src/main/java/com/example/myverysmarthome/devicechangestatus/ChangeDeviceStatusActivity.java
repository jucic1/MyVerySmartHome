package com.example.myverysmarthome.devicechangestatus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.databinding.ActivityChangeDeviceStatusBinding;
import com.example.myverysmarthome.model.devices.Camera;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.Fan;
import com.example.myverysmarthome.model.devices.Level;
import com.example.myverysmarthome.model.devices.Light;
import com.example.myverysmarthome.model.devices.Plug;
import com.example.myverysmarthome.model.devices.Thermostat;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeDeviceStatusActivity extends AppCompatActivity {
    final static String EXTRA_ITEM_KEY = "extra_item_key";

    public static Intent getIntent(Context context, Device item) {
        Intent intent = new Intent(context, ChangeDeviceStatusActivity.class);
        intent.putExtra(EXTRA_ITEM_KEY, item);
        return intent;
    }

    ActivityChangeDeviceStatusBinding activityChangeDeviceStatusBinding;
    ChangeDeviceStatusViewModel changeDeviceStatusViewModel;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangeDeviceStatusBinding = ActivityChangeDeviceStatusBinding.inflate(getLayoutInflater());
        setContentView(activityChangeDeviceStatusBinding.getRoot());
        changeDeviceStatusViewModel = new ViewModelProvider(this).get(ChangeDeviceStatusViewModel.class);

        sharedPreferences = getApplicationContext().getSharedPreferences("PRIMITIVE", Context.MODE_PRIVATE);

        final Device item = (Device) getIntent().getSerializableExtra(EXTRA_ITEM_KEY);

        activityChangeDeviceStatusBinding.deviceNameText.setText(item.getName());
        String status = item.getValue().toString();
        if (item instanceof Fan) {
            status = ((Level) item.getValue()).toPolish();
        }
        activityChangeDeviceStatusBinding.deviceStatusText.setText(status);
        int drawableId = DataContainer.getInstance().getCategoryForDevice(item).getDrawableId();
        activityChangeDeviceStatusBinding.deviceStateImage.setImageDrawable(getDrawable(drawableId));

        getElement(item);

        activityChangeDeviceStatusBinding.forgetDevice.setOnClickListener(view -> {
            changeDeviceStatusViewModel.removeItem(item);
            finish();
        });

        changeDeviceStatusViewModel.feedback.observe(this, message -> {
            Snackbar.make(activityChangeDeviceStatusBinding.getRoot(), message, Snackbar.LENGTH_LONG).show();
        });
    }

    void getElement(Device device) {
        if (device instanceof Light || device instanceof Camera || device instanceof Plug) {
            addSwitch(device);
        } else if (device instanceof Thermostat) {
            addSlider(device);
        } else if (device instanceof Fan) {
            addRadioButtons(device);
        } else {
            throw new IllegalStateException("Not known instance type");
        }
    }

    private void addRadioButtons(Device device) {
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.WHITE, Color.WHITE,
                }
        );
        RadioGroup radioGroup = new RadioGroup(this);
//        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        Level currentLevel = (Level) DataContainer.getInstance().getDevice(device.getUuid()).getValue();
        int idOfCheckedButton = 0;
        for (Level level : Level.values()) {
            RadioButton categoryRadioButton = new RadioButton(this);
            categoryRadioButton.setTextColor(Color.WHITE);
            categoryRadioButton.setTextSize(18);
            categoryRadioButton.setId(View.generateViewId());
            categoryRadioButton.setPadding(15, 15, 15, 15);
            categoryRadioButton.setText(level.toPolish());
            categoryRadioButton.setButtonTintList(colorStateList);
            if (level == currentLevel) {
                idOfCheckedButton = categoryRadioButton.getId();
            }
            radioGroup.addView(categoryRadioButton);
        }
        radioGroup.check(idOfCheckedButton);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                Level newLevel = Level.toEnglish((String) checkedRadioButton.getText());
                DataContainer.getInstance().getDevice(device.getUuid()).setValue(newLevel);
                activityChangeDeviceStatusBinding.deviceStatusText.setText(checkedRadioButton.getText());
                changeDeviceStatusViewModel.sendStatusChange(device.getName(), "set_intensity", newLevel.toString().toLowerCase(), sharedPreferences);
            }
        });
        activityChangeDeviceStatusBinding.container.addView(radioGroup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void addSlider(Device device) {
        Slider slider = new Slider(this);
        slider.setValueFrom(15);
        slider.setValueTo(30);
        slider.setValue(((Thermostat) device).getValue());
        slider.setScaleX((float) 0.5);
        slider.setScaleY(2);
        slider.addOnChangeListener((slider1, value, fromUser) -> {
            Float newStatus = Float.valueOf(String.format("%.2f", value));
            DataContainer.getInstance().getDevice(device.getUuid()).setValue(newStatus);
            String newStringStatus = DataContainer.getInstance().getDevice(device.getUuid()).getValue().toString();
            activityChangeDeviceStatusBinding.deviceStatusText.setText(newStringStatus);
            changeDeviceStatusViewModel.sendStatusChange(device.getName(), "set_temperature", newStringStatus, sharedPreferences);
        });
        activityChangeDeviceStatusBinding.container.addView(slider, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void addSwitch(Device device) {
        SwitchCompat someSwitch = new SwitchCompat(this);
        Boolean status = (Boolean) device.getValue();
        someSwitch.setChecked(status);
        someSwitch.setScaleX(3);
        someSwitch.setScaleY(3);
        someSwitch.setOnCheckedChangeListener((compoundButton, status1) -> {
            DataContainer.getInstance().getDevice(device.getUuid()).setValue(status1);
            Boolean newStatus = (Boolean) DataContainer.getInstance().getDevice(device.getUuid()).getValue();
            activityChangeDeviceStatusBinding.deviceStatusText.setText(newStatus.toString());
            changeDeviceStatusViewModel.sendStatusChange(device.getName(), "activate", newStatus.toString(), sharedPreferences);
        });
        activityChangeDeviceStatusBinding.container.addView(someSwitch, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
