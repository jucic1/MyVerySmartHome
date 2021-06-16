package com.example.myverysmarthome.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;
    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        activityRegisterBinding.registerButton.setOnClickListener(view -> {
            registerViewModel.register(activityRegisterBinding.nameInput.getText().toString(),
                    activityRegisterBinding.surnameInput.getText().toString(), activityRegisterBinding.emailInput.getText().toString(),
                    activityRegisterBinding.passwordInput.getText().toString(), activityRegisterBinding.passwordRepeatedInput.getText().toString());
        });

        registerViewModel.nameValidation.observe(this, validationMessage -> {
            activityRegisterBinding.nameLayout.setError(validationMessage);
        });
        registerViewModel.surnameValidation.observe(this, validationMessage -> {
            activityRegisterBinding.surnameLayout.setError(validationMessage);
        });
        registerViewModel.emailValidation.observe(this, validationMessage -> {
            activityRegisterBinding.emailLayout.setError(validationMessage);
        });
        registerViewModel.passwordValidation.observe(this, validationMessage -> {
            activityRegisterBinding.passwordLayout.setError(validationMessage);
        });
        registerViewModel.passwordRepeatedValidation.observe(this, validationMessage -> {
            activityRegisterBinding.passwordRepeatedLayout.setError(validationMessage);
        });
        registerViewModel.errorMessage.observe(this, errorMessage -> {
            Snackbar.make(activityRegisterBinding.getRoot(),errorMessage, Snackbar.LENGTH_LONG).show();
        });
        registerViewModel.navigateToHome.observe(this, validationMessage -> {
            //fill this out
        });
    }
}

