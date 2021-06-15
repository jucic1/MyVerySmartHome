package com.example.myverysmarthome.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myverysmarthome.databinding.ActivityLogInBinding;
import com.google.android.material.snackbar.Snackbar;

public class LogInActivity extends AppCompatActivity {

    ActivityLogInBinding activityLogInBinding;
    LogInViewModel logInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLogInBinding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(activityLogInBinding.getRoot());

        logInViewModel = new ViewModelProvider(this).get(LogInViewModel.class);

        activityLogInBinding.logInButton.setOnClickListener(view -> {
            logInViewModel.logIn(activityLogInBinding.emailInput.getText().toString(),
                    activityLogInBinding.passwordInput.getText().toString());
        });
        activityLogInBinding.registerButton.setOnClickListener(view -> {
            ///load another activity
        });

        logInViewModel.emailValidation.observe(this, validationMessage -> {
            activityLogInBinding.emailLayout.setError(validationMessage);
        });
        logInViewModel.passwordValidation.observe(this, validationMessage -> {
            activityLogInBinding.passwordLayout.setError(validationMessage);
        });
        logInViewModel.errorMessage.observe(this, errorMessage -> {
            Snackbar.make(activityLogInBinding.getRoot(),errorMessage, Snackbar.LENGTH_LONG).show();
        });
        logInViewModel.navigateToHome.observe(this, validationMessage -> {
            //fill this out
        });
    }
}
