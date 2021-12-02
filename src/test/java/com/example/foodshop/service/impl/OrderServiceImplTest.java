package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.*;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private OrderServiceImpl serviceToTest;
    private OrderEntity orderEntity;
    private ProductEntity product;
    private CartEntity cart;
    private UserEntity user;
    private RoleEntity userRole;

    @Mock
    private OrderRepository mockOrderRepository;

    @BeforeEach
    void init(){

        userRole = new RoleEntity();
        userRole.setRole(RoleNameEnum.USER);

        user = new UserEntity();
        user.setUsername("Vlado").setEmail("vlado@vlado.com").setAddress("vlado132").setFullName("VLado Sharkov")
                .setPassword("12345").setRoles(Set.of(userRole));

        product = new ProductEntity();
        product.setCategory(CategoryNameEnum.BIO).setName("tea")
                .setDescription("dada").setQuantity(2).setImageUrl("image").setPrice(BigDecimal.valueOf(1))
                .setId(1L);

        cart = new CartEntity();
        cart.setUser(user).setTotalPrice(BigDecimal.valueOf(3)).setOrders(List.of(orderEntity));

        serviceToTest = new OrderServiceImpl(mockOrderRepository);
        orderEntity =new OrderEntity();
        orderEntity.setCount(2)
                .setProducts(product)
                .setTotalPrice(BigDecimal.valueOf(3))
                .setCart(cart).setId(1L);

    }


}