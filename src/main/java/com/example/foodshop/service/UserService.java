package com.example.foodshop.service;

import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.service.UserRegistrationServiceModel;

import java.security.Principal;

public interface UserService {

    void initializeRoles();


    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    void initializeAdmin();

    UserEntity getUserByUsername(String name);


}
