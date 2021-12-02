package com.example.foodshop.model.service;

public class UserRegistrationServiceModel {

    private String username;
    private String fullName;
    private String email;
    private String address;
    private String password;

    public UserRegistrationServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserRegistrationServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
