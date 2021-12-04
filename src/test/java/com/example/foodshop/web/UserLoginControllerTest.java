package com.example.foodshop.web;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.repository.CartRepository;
import com.example.foodshop.repository.RoleRepository;
import com.example.foodshop.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CartRepository cartRepository;



    @BeforeEach
    public void init(){
        RoleEntity role = new RoleEntity();
        role.setRole(RoleNameEnum.USER);
        roleRepository.save(role);



        UserEntity user =new UserEntity();
        user.setUsername("vlado").setPassword("12345").setEmail("vlado@valdo.com").setAddress("vlado123")
                .setRoles(Set.of(role)).setFullName("vlado vlado");
        userRepository.save(user);
    }

    @AfterEach
    public void clean(){
        userRepository.deleteAll();
    }

    @Test
    void testOpenLoginForm() throws Exception {
        mockMvc
                .perform(
                        get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }



}