package com.example.myverysmarthome.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.apache.commons.validator.routines.EmailValidator;

public class RegisterViewModel extends ViewModel {

    MutableLiveData<String> surnameValidation = new MutableLiveData<>();
    MutableLiveData<String> nameValidation = new MutableLiveData<>();
    MutableLiveData<String> emailValidation = new MutableLiveData<>();
    MutableLiveData<String> passwordValidation = new MutableLiveData<>();
    MutableLiveData<String> passwordRepeatedValidation = new MutableLiveData<>();

    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> navigateToHome = new MutableLiveData<>(false);

    void register(String name, String surname, String email, String password, String passwordRepeated) {
        if(isValid(name, surname, email, password, passwordRepeated)){
            navigateToHome.setValue(true);
        }else{
            errorMessage.setValue("Cannot register, correct the errors.");
        }
    }

    private boolean isValid(String name, String surname, String email, String password, String passwordRepeated){
        boolean isValidated = true;

        if(name.isEmpty()){
            nameValidation.setValue("Your name cannot be empty");
            isValidated = false;
        }
        if(surname.isEmpty()){
            surnameValidation.setValue("Your surname cannot be empty");
            isValidated = false;
        }
        if(email.isEmpty()){
            emailValidation.setValue("Your email cannot be empty");
            isValidated = false;
        }
        if(password.isEmpty()){
            passwordValidation.setValue("Your password cannot be empty");
            isValidated = false;
        }
        if(passwordRepeated.isEmpty()) {
            passwordRepeatedValidation.setValue("Repeat your password");
            isValidated = false;
        }
        if(!email.isEmpty() && !isValidEmail(email)) {
            emailValidation.setValue("Email in wrong format. Correct one: xx@xx.x");
            isValidated = false;
        }
        if(!password.equals(passwordRepeated)) {
            passwordValidation.setValue("Passwords don't match");
            passwordRepeatedValidation.setValue("Passwords don't match");
            isValidated = false;
        }
        return isValidated;
    }

    private boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
