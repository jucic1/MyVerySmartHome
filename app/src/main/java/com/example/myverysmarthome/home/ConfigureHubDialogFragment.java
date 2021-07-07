package com.example.myverysmarthome.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myverysmarthome.R;
import com.google.android.material.textfield.TextInputEditText;

public class ConfigureHubDialogFragment extends DialogFragment {

    private TextInputEditText phoneNumber;
    private Button configureButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phoneNumber = view.findViewById(R.id.phoneNumberInput);
        configureButton = view.findViewById(R.id.configureButton);
        configureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configureHubDeviceListener.onConfigure(phoneNumber.getText().toString());
            }
        });
    }

    ConfigureHubDeviceListener configureHubDeviceListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            configureHubDeviceListener = (ConfigureHubDeviceListener) requireActivity();
        }catch (Exception e) {
            throw new IllegalStateException("Listener is not attached");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_configure_hub, container, false);
        return view;
    }
}

interface ConfigureHubDeviceListener{
    void onConfigure(String phoneNumber);
}