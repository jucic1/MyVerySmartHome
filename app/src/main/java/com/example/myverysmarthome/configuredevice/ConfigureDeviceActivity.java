package com.example.myverysmarthome.configuredevice;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.databinding.ActivityConfigureDeviceBinding;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.devices.Level;
import com.example.myverysmarthome.model.devices.Thermostat;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;


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
            if (configureDevice) {
                finish();
            }
        });

        configureDeviceViewModel.snackBarMessage.observe(this, snackBarMessage -> {
            Snackbar.make(activityConfigureDeviceBinding.getRoot(), snackBarMessage, Snackbar.LENGTH_LONG).show();
        });

        activityConfigureDeviceBinding.addDeviceButton.setOnClickListener(view -> {
            configureDeviceViewModel.configureDevice(activityConfigureDeviceBinding.nameInput.getText().toString());
        });
    }

    public void addCategoryRadioButtons() {
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.WHITE, Color.rgb(217, 125, 84),
                }
        );

        for (Category category : DataContainer.getInstance().categories) {
            if (category.getDeviceType() != null) {
                RadioButton categoryRadioButton = new RadioButton(this);
                categoryRadioButton.setTextColor(Color.WHITE);
                categoryRadioButton.setTextSize(18);
                categoryRadioButton.setPadding(15, 15, 15, 15);
                categoryRadioButton.setButtonTintList(colorStateList);
                categoryRadioButton.setText(category.getTitle());
                categoryRadioButton.setOnClickListener(view -> {
                    if (((RadioButton) view).isChecked()) {
                        activityConfigureDeviceBinding.container.removeAllViews();
                        configureDeviceViewModel.setCategory(category);
                        TextView textView = new TextView(this);
                        textView.setText("Aktualny stan: ");
                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(20);
                        activityConfigureDeviceBinding.container.addView(textView);
                        activityConfigureDeviceBinding.container.addView(getViewElementToAdd(category));
                    }
                });
                activityConfigureDeviceBinding.deviceCategoryRadioGroup.addView(categoryRadioButton);
            }
        }
    }

    private View getViewElementToAdd(Category category) {
        switch (category.getDeviceType()) {
            case LIGHT:
            case PLUG:
            case CAMERA:
                return createStatusDropdown(Arrays.asList("true", "false"));
            case FAN:
                return createStatusDropdown(Level.getAllInPolish());
            case THERMOSTAT:
                return createSlider();
            default:
                throw new IllegalArgumentException();
        }
    }

    private View createSlider() {
        Slider slider = new Slider(this);
        slider.setValueFrom(15);
        slider.setValue(22);
        slider.setValueTo(30);
        slider.setScaleX((float) 0.5);
        slider.addOnChangeListener((slider1, value, fromUser) ->
                configureDeviceViewModel.setCurrentStatus(String.format("%.2f", value)));
        return slider;
    }


    private View createStatusDropdown(List<String> possibleValues) {
        Spinner dropdown = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, possibleValues);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                configureDeviceViewModel.setCurrentStatus(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return dropdown;
    }
}
