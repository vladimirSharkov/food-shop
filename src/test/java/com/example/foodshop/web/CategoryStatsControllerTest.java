package com.example.foodshop.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryStatsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    @Test
    void testStatistics() throws Exception {
        mockMvc.perform(get("/statistic/category"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stats"))
                .andExpect(view().name("category-stats"));
    }

}