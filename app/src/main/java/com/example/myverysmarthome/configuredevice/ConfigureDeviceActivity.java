package com.example.myverysmarthome.configuredevice;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.databinding.ActivityConfigureDeviceBinding;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.devices.Level;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

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
            Snackbar.make(activityConfigureDeviceBinding.getRoot(), validationMessage, Snackbar.LENGTH_LONG).show();
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
                        textView.setPadding(0, 0, 20, 0);
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
                return createEditText(15, 30, 20);
            default:
                throw new IllegalArgumentException();
        }
    }

    private View createEditText(int minValue, int maxValue, int defaultTemp) {
        EditText input = new EditText(this);
        configureDeviceViewModel.setCurrentStatus(String.valueOf(defaultTemp));
        input.setText(String.valueOf(defaultTemp));
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_focused},
                new int[]{android.R.attr.state_focused},
        };
        int[] colors = new int[]{
                ContextCompat.getColor(getBaseContext(), R.color.orange),
                ContextCompat.getColor(getBaseContext(), R.color.orange),
        };
        ColorStateList myList = new ColorStateList(states, colors);
        input.setBackgroundTintList(myList);
        input.setTextColor(Color.WHITE);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    Float temp = Float.valueOf(charSequence.toString());
                    if (temp < minValue || temp > maxValue) {
                        Snackbar.make(activityConfigureDeviceBinding.getRoot(), "Temperatura musi znajdować się w przedziale od 15\u2103 do 30\u2103", Snackbar.LENGTH_LONG).show();
                    } else {
                        configureDeviceViewModel.setCurrentStatus(temp.toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        return input;
    }

    private View createStatusDropdown(List<String> possibleValues) {
        Spinner dropdown = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_spinner, possibleValues);
        dropdown.setAdapter(adapter);
        dropdown.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getItemAtPosition(position).toString();
                if (possibleValues.size() > 2) {
                    configureDeviceViewModel.setCurrentStatus(Level.toEnglish(selected).toString());
                } else {
                    configureDeviceViewModel.setCurrentStatus(selected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return dropdown;
    }
}
