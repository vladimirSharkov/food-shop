package com.example.foodshop.web;

import com.example.foodshop.model.enumeration.CategoryNameEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAllProductView() throws Exception {
        mockMvc
                .perform(get("/product/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("product"));
    }

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAllBioProductView() throws Exception {
        mockMvc
                .perform(get("/product/bio"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("bio-product"));
    }

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAllMeatProductView() throws Exception {
        mockMvc
                .perform(get("/product/meat"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("meat-product"));
    }

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAllDrinksProductView() throws Exception {
        mockMvc
                .perform(get("/product/drinks"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("drinks-product"));
    }

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAllDairyProductView() throws Exception {
        mockMvc
                .perform(get("/product/dairy"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("dairy-product"));
    }

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAllBreadProductView() throws Exception {
        mockMvc
                .perform(get("/product/bread"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("bread-product"));
    }

    @Test
    void testAdd() throws Exception {
        mockMvc.perform(get("/product/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"));
    }

    @Test
    void testEditView() throws Exception {
        mockMvc.perform(get("/product/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allProduct"))
                .andExpect(view().name("edit"));
    }
    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testAddProduct() throws Exception {
        mockMvc.perform(post("/product/add")
                .param("name","")
                .param("price","1")
                .param("imageUrl" ,"dfffff")
                .param("category","BIO")
                .param("description","ajjj")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("addProductBindingModel",
                        "org.springframework.validation.BindingResult.addProductBindingModel"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("add"));

    }


}