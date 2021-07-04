package com.example.myverysmarthome.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the Builder class for convenient dialog construction
////        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////        builder.setMessage("lol")
////                .setPositiveButton("fire", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        // FIRE ZE MISSILES!
////                    }
////                })
////                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        // User cancelled the dialog
////                    }
////                });
//        // Create the AlertDialog object and return it
//        return builder.create();
//    }

//    private EditText editText;
    private TextInputEditText hubUuid;
    private TextInputEditText mac;
    private Button configureButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hubUuid = view.findViewById(R.id.hubInput);
        mac = view.findViewById(R.id.macInput);
        configureButton = view.findViewById(R.id.configureButton);
        configureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configureHubDeviceListener.onConfigure(mac.getText().toString(),hubUuid.getText().toString());
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
    void onConfigure(String mac, String uuid);
}