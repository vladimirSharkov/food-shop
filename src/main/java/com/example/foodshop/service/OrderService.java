package com.example.foodshop.service;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;


public interface OrderService {

    OrderEntity createOrder(ProductEntity product, CartEntity cartEntity, Integer quantity);

    void deleteOrder(Long id);
}