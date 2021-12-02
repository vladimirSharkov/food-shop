package com.example.foodshop.web;

import com.example.foodshop.model.entity.*;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.repository.CartRepository;
import com.example.foodshop.repository.OrderRepository;
import com.example.foodshop.repository.ProductRepository;
import com.example.foodshop.repository.RoleRepository;
import com.example.foodshop.repository.UserRepository;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    private final static String CART_PREFIX = "/carts";
    private CartController testController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    private CartEntity cartEntity;
    @Autowired
    private CartRepository cartRepository;
    private UserEntity userEntity;
    @Autowired
    private UserRepository userRepository;
    private OrderEntity orderEntity;
    @Autowired
    private OrderRepository orderRepository;
    private ProductEntity productEntity;
    @Autowired
    private ProductRepository productRepository;
    private RoleEntity roleEntity;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        init();
    }


    void init() {

        roleEntity = new RoleEntity();
        roleEntity.setRole(RoleNameEnum.USER);

        userEntity = new UserEntity();
        userEntity
                .setEmail("vlado@asd")
                .setAddress("vlado123")
                .setUsername("vlado")
                .setFullName("vlado vlado")
                .setPassword("vlado")
                .setRoles(Set.of(roleEntity))
                .setId(1L);

        productEntity = new ProductEntity();
        productEntity
                .setImageUrl("asdfg")
                .setCategory(CategoryNameEnum.BIO)
                .setQuantity(1)
                .setPrice(BigDecimal.valueOf(3))
                .setDescription("eeeeeee")
                .setName("tea")
                .setId(1L);

        orderEntity = new OrderEntity();
        orderEntity
                .setTotalPrice(BigDecimal.valueOf(3))
                .setProducts(productEntity)
                .setCount(1)
                .setId(1L);

        cartEntity = new CartEntity();
        cartEntity.setTotalPrice(BigDecimal.valueOf(3)).setUser(userEntity).setOrders(List.of(orderEntity)).setId(1L);
        userEntity.setCart(cartEntity);
        orderEntity.setCart(cartEntity);


//        productEntity = productRepository.save(productEntity);
//        orderEntity = orderRepository.save(orderEntity);
//        cartEntity = cartRepository.save(cartEntity);
//        userEntity = userRepository.save(userEntity);

    }

    @AfterEach
    void tearDown() {

    }

//    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
//    @Test
//    void testAddViewCart() throws Exception {
//        mockMvc
//                .perform(get("/carts/add"))
//                .andExpect(model().attributeExists("orders"))
//                .andExpect(model().attributeExists("cart"))
//                .andExpect(model().attributeExists("totalPrice"))
//                .andExpect(view().name("cart"));
//    }

    //    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
//    void testGetCart() throws Exception {
//        mockMvc.perform(get(CART_PREFIX + "/add")).andExpect(status().isOk());
//    }
//    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
//    @Test
//    void testBuy() throws Exception {
//        mockMvc.perform(get("/carts/buy"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/"));
//    }
//    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
//    @Test
//    void testBuy() throws Exception {
//        mockMvc.perform(get("/carts/buy"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/"));
//    }
}