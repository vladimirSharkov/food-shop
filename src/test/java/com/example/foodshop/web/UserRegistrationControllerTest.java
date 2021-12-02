package com.example.foodshop.web;

import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc
                .perform(
                        get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

//    @Test
//    void testRegisterUser() throws Exception {
//
//        mockMvc
//                .perform(
//                        post("/users/register")
//                                .param("username", "vlado")
//                                .param("fullName", "vlado vlado")
//                                .param("email", "vlado@avlado.com")
//                                .param("address", "vlado123")
//                                .param("password", "12345")
//                                .param("confirmPassword", "12345")
//                                .with(csrf())
//                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)).
//                andExpect(status().is3xxRedirection())
//                        .andExpect(view().name("/users/login"));
//
//        Assertions.assertEquals(2, userRepository.count());
//        Optional<UserEntity> newCreatedUser = userRepository.findByUsername("vlado");
//        Assertions.assertTrue(newCreatedUser.isPresent());
//
//    }

    @Test
    void test() throws Exception {
        mockMvc.perform(post("/users/register")
                                .param("username", "vlado")
                                .param("fullName", "vlado vlado")
                                .param("email", "vlado@avlado.com")
                                .param("address", "vlado123")
                                .param("password", "123")
                                .param("confirmPassword", "12345")
                                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("userModel",
                        "org.springframework.validation.BindingResult.userModel"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users/register"));
    }
}