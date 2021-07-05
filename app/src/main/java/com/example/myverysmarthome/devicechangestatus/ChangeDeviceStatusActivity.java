package com.example.myverysmarthome.devicechangestatus;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.databinding.ActivityChangeDeviceStatusBinding;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Device;

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
//        activityChangeDeviceStatusBinding.switch1.setChecked(item.getStatus());
//
//        activityChangeDeviceStatusBinding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
//                changeDeviceStatusViewModel.statusChange(item.getUuid(), status);
//            }
//        });

        getElement();

        activityChangeDeviceStatusBinding.forgetDevice.setOnClickListener(view -> {
            changeDeviceStatusViewModel.removeItem(item);
            finish();
        });
    }

    void getElement() {
//       SwitchCompat someSwitch = new SwitchCompat(this);
//       someSwitch.setScaleX(3);
//       someSwitch.setScaleY(3);
//       activityChangeDeviceStatusBinding.container.addView(someSwitch,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

//        Slider slider = new Slider(this);
//        slider.setValueFrom(15);
//        slider.setValueTo(30);
//        slider.setValue(20);
//        slider.setScaleX((float) 0.5);
//        slider.setScaleY(2);
//        activityChangeDeviceStatusBinding.container.addView(slider, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


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
        ArrayList<String> options =  new ArrayList<String>( Arrays.asList("Lagodny", "Sredni", "Mocny"));
        for (String option : options) {
            RadioButton categoryRadioButton = new RadioButton(this);
            categoryRadioButton.setTextColor(Color.WHITE);
            categoryRadioButton.setTextSize(18);
            categoryRadioButton.setPadding(15, 15, 15, 15);
            categoryRadioButton.setText(option);
            categoryRadioButton.setButtonTintList(colorStateList);
//                categoryRadioButton.setOnClickListener(view -> {
//                    if (((RadioButton) view).isChecked()) {
//                        configureDeviceViewModel.setCategory(category);
//                    }
//                });

            radioGroup.addView(categoryRadioButton);
        }
        activityChangeDeviceStatusBinding.container.addView(radioGroup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

}