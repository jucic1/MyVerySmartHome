package com.example.myverysmarthome.devicechangestatus;

import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangeDeviceStatusBinding = ActivityChangeDeviceStatusBinding.inflate(getLayoutInflater());
        setContentView(activityChangeDeviceStatusBinding.getRoot());
        changeDeviceStatusViewModel = new ViewModelProvider(this).get(ChangeDeviceStatusViewModel.class);

        final Device item = (Device) getIntent().getSerializableExtra(EXTRA_ITEM_KEY);

        activityChangeDeviceStatusBinding.deviceNameText.setText(item.getName());
        activityChangeDeviceStatusBinding.deviceStatusText.setText(item.getValue().toString());

        getElement(item);

        activityChangeDeviceStatusBinding.forgetDevice.setOnClickListener(view -> {
            changeDeviceStatusViewModel.removeItem(item);
            finish();
        });
    }

    void getElement(Device device) {
        if (device instanceof Light || device instanceof Camera || device instanceof Plug) {
            SwitchCompat someSwitch = new SwitchCompat(this);
            Boolean status = (Boolean) device.getValue();
            someSwitch.setChecked(status);
            someSwitch.setScaleX(3);
            someSwitch.setScaleY(3);
            someSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                    DataContainer.getInstance().getDevice(device.getUuid()).setValue(status);
                    Boolean newStatus = (Boolean) DataContainer.getInstance().getDevice(device.getUuid()).getValue();
                    activityChangeDeviceStatusBinding.deviceStatusText.setText(newStatus.toString());
//                    changeDeviceStatusViewModel.statusChange(item.getUuid(), status);
                }
            });
            activityChangeDeviceStatusBinding.container.addView(someSwitch, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (device instanceof Thermostat) {
            Slider slider = new Slider(this);
            slider.setValueFrom(15);
            slider.setValueTo(((Thermostat) device).getValue());
            slider.setValue(20);
            slider.setScaleX((float) 0.5);
            slider.setScaleY(2);
            slider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(Slider slider, float value, boolean fromUser) {
                    DataContainer.getInstance().getDevice(device.getUuid()).setValue(value);
                    Float newStatus = (Float) DataContainer.getInstance().getDevice(device.getUuid()).getValue();
                    activityChangeDeviceStatusBinding.deviceStatusText.setText(newStatus.toString());
                }
            });
            activityChangeDeviceStatusBinding.container.addView(slider, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (device instanceof Fan) {
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
            radioGroup.setOrientation(RadioGroup.HORIZONTAL);
            Level currentLevel = (Level) DataContainer.getInstance().getDevice(device.getUuid()).getValue();
            int idOfCheckedButton = 0;
            for (Level level : Level.values()) {
                RadioButton categoryRadioButton = new RadioButton(this);
                categoryRadioButton.setTextColor(Color.WHITE);
                categoryRadioButton.setTextSize(18);
                categoryRadioButton.setId(View.generateViewId());
                categoryRadioButton.setPadding(15, 15, 15, 15);
                categoryRadioButton.setText(level.toString());
                categoryRadioButton.setButtonTintList(colorStateList);
                if(level == currentLevel) {
                    idOfCheckedButton = categoryRadioButton.getId();
                }
                radioGroup.addView(categoryRadioButton);
            }
            radioGroup.check(idOfCheckedButton);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                        Level newLevel = Level.valueOf((String) checkedRadioButton.getText());
                        DataContainer.getInstance().getDevice(device.getUuid()).setValue(newLevel);
                        activityChangeDeviceStatusBinding.deviceStatusText.setText(checkedRadioButton.getText());
                    }
            });
            activityChangeDeviceStatusBinding.container.addView(radioGroup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        } else {
            throw new IllegalStateException("Not known instance type");
        }

    }
}