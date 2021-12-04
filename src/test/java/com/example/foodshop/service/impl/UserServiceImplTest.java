package com.example.foodshop.service.impl;

import com.example.foodshop.model.binding.UserRegistrationBindingModel;
import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.model.service.UserRegistrationServiceModel;
import com.example.foodshop.repository.RoleRepository;
import com.example.foodshop.repository.UserRepository;
import com.example.foodshop.service.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserEntity testUser;
    private UserServiceImpl serviceToTest;
    @Mock
    private CartService testCartService;
    @Mock
    private PasswordEncoder testPasswordEncoder;
    @Mock
    private RoleRepository testRoleRepository;

    private RoleEntity adminRole, userRole;
    private UserRegistrationServiceModel userRegistrationServiceModel;

    @Mock
    private UserRepository mockUserRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoodShoppUserDetailsService foodShoppUserDetailsService;

    UserServiceImplTest() {
    }

    @BeforeEach
    void init() {
        serviceToTest = new UserServiceImpl(mockUserRepository, testPasswordEncoder, testRoleRepository, testCartService, modelMapper, foodShoppUserDetailsService);
        adminRole = new RoleEntity();
        adminRole.setRole(RoleNameEnum.ADMIN);

        userRole = new RoleEntity();
        userRole.setRole(RoleNameEnum.USER);

        testUser = new UserEntity();
        testUser.setUsername("Vlado").setEmail("vlado@vlado.com").setAddress("vlado132").setFullName("VLado Sharkov")
                .setPassword("12345").setRoles(Set.of( userRole));

        userRegistrationServiceModel = new
                UserRegistrationServiceModel();
        userRegistrationServiceModel.setUsername("Vlado").setEmail("vlado@vlado.com").setAddress("vlado132").setFullName("VLado Sharkov")
                .setPassword("12345");

        mockUserRepository.save(testUser);
        testRoleRepository.save(userRole);
    }

    @AfterEach
    void clean(){
        mockUserRepository.deleteAll();
        testRoleRepository.deleteAll();
    }
    @Test
    void testGetUserByUsername() {
        Mockito
                .when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        Optional<UserEntity> userEntity = mockUserRepository.findByUsername(testUser.getUsername());
        UserEntity user = serviceToTest.getUserByUsername(testUser.getUsername());
        Assertions.assertEquals(Optional.of(user), userEntity);
    }



}