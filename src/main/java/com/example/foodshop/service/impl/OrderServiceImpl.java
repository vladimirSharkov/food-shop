package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.repository.OrderRepository;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderEntity createOrder(ProductEntity product, CartEntity cartEntity, Integer quantity) {
        OrderEntity order = new OrderEntity();
        order.setCount(quantity);
        order.setCart(cartEntity);
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        order.setProducts(product);

        orderRepository.save(order);

        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteOrderById(id);
    }
}
