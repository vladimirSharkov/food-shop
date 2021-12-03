package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.repository.OrderRepository;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;


    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }


    @Override
    public OrderEntity createOrder(ProductEntity product, CartEntity cartEntity, Integer quantity)  {


        OrderEntity order = new OrderEntity();
        order.setCart(cartEntity);
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        order.setCount(quantity);
        productService.setQuantity(product);
        order.setProducts(product);

        orderRepository.save(order);

        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteOrderById(id);
    }
}
