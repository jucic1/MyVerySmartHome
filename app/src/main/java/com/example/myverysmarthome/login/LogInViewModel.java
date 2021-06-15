package com.example.myverysmarthome.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.apache.commons.validator.routines.EmailValidator;

public class LogInViewModel extends ViewModel {

    MutableLiveData<String> emailValidation = new MutableLiveData<>();
    MutableLiveData<String> passwordValidation = new MutableLiveData<>();

    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> navigateToHome = new MutableLiveData<>(false);

    void logIn(String email, String password) {
        if (isValid(email, password)) {
//            if (authenticate(email, password)) { //unsuccessfull authentication
//                navigateToHome.setValue(true);
//            } else {
//                errorMessage.setValue("Cannot log in, Wrong password and email combination");
//            }
        } else {
            errorMessage.setValue("Cannot log in, correct the errors.");
        }
    }

    private boolean isValid(String email, String password) {
        boolean isValidated = true;

        if (email.isEmpty()) {
            emailValidation.setValue("Your email cannot be empty");
            isValidated = false;
        }
        if (password.isEmpty()) {
            passwordValidation.setValue("Your password cannot be empty");
            isValidated = false;
        }
        if(!email.isEmpty() && !isValidEmail(email)) {
            emailValidation.setValue("Email in wrong format. Correct one: xx@xx.x");
            isValidated = false;
        }
        return isValidated;
    }

    private boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
