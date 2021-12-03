package com.example.foodshop.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {

    private String username;
    private String fullName;
    private String email;
    private String address;
    private String password;
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }
    @NotBlank(message = "Username cannot be null or empty String!!!")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters!!!")
    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
    @NotBlank(message = "Full name cannot be null or empty String!!!")
    public String getFullName() {
        return fullName;
    }

    public UserRegistrationBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    @NotBlank(message = "Email cannot be null or empty String!!!")
    @Email
    @Size(min = 3, message = "Email must be more than 3 characters!!!")
    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
    @NotBlank(message = "Address cannot be null or empty String!!!")
    @Size(min = 5, max = 50, message = "Address must be between 5 and 50 characters!!!")
    public String getAddress() {
        return address;
    }

    public UserRegistrationBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }
    @NotBlank(message = "Password cannot be null or empty String!!!")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters!!!")
    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
    @NotBlank(message = "Password cannot be null or empty String!!!")
    @Size(min = 5, max = 30, message = "Confirm password must be between 5 and 30 characters!!!")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
