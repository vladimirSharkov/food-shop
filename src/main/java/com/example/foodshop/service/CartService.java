package com.example.foodshop.service;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import javassist.tools.rmi.ObjectNotFoundException;

import java.math.BigDecimal;

public interface CartService {


    void productAddToCart(UserEntity user, ProductEntity product, Integer quantity) ;

    CartEntity createCart(UserEntity user);


    void setTotalPrice(CartEntity cartEntity, BigDecimal total);

    void deleteAllOrder(UserEntity user);


    void deleteOrder(Long id);
}
