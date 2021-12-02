package com.example.foodshop.web;

import com.example.foodshop.model.binding.CommentBindingModel;
import com.example.foodshop.model.entity.CommentEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import com.example.foodshop.repository.CommentRepository;
import com.example.foodshop.repository.ProductRepository;
import com.example.foodshop.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WithMockUser("vlado")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

    private static final String TEST1 = "something ... test";
    private static final String TEST2 = "something else ... test";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity testUser;


    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setPassword("12345")
                .setUsername("vlado").setFullName("vlado sharkov").setAddress("vlado123").setEmail("vlado@vlad");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        Long productId = initComments(initProduct());

        mockMvc.perform(get("/api/" + productId + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(TEST1)))
                .andExpect(jsonPath("$.[1].message", is(TEST2)));
    }

    @Test
    void testCreatedComments() throws Exception {
        CommentBindingModel testComment = new CommentBindingModel().setMessage(TEST1);

        var emptyProduct = initProduct();
        mockMvc.perform(
                        post("/api/" + emptyProduct.getId() + "/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testComment))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyProduct.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(TEST1)));
    }


    private ProductEntity initProduct() {
        ProductEntity product = new ProductEntity();
        product.setName("tea").setPrice(BigDecimal.valueOf(1)).setQuantity(1).setCategory(CategoryNameEnum.BIO)
                .setDescription("ddddddddd").setImageUrl("aaaaaaaaaa");
        return productRepository.save(product);
    }


    private Long initComments(ProductEntity productEntity) {


        CommentEntity comment1 = new CommentEntity();
        comment1.setAuthor(testUser).setTextContent(TEST1).setApproved(true).setProduct(productEntity);

        CommentEntity comment2 = new CommentEntity();
        comment2.setAuthor(testUser).setTextContent(TEST2).setApproved(true).setProduct(productEntity);

        productEntity.setComments(List.of(comment1, comment2));


        return productRepository.save(productEntity).getId();
    }

}