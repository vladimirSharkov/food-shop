package com.example.foodshop.service.impl;

import com.example.foodshop.repository.CartRepository;
import com.example.foodshop.repository.OrderRepository;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    private CartServiceImpl serviceToTest;

    @Mock
    private  CartRepository cartRepository;
    @Mock
    private  OrderService orderService;
    @Mock
    private  ProductService productService;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void init(){
        serviceToTest=new CartServiceImpl(cartRepository,orderService,productService, orderRepository);
    }

}