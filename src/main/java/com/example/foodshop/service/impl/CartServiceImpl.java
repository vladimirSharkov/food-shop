package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.repository.CartRepository;
import com.example.foodshop.repository.OrderRepository;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public CartServiceImpl(CartRepository cartRepository, OrderService orderService, ProductService productService, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderService = orderService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public void productAddToCart(UserEntity user, ProductEntity product, Integer quantity) {
        CartEntity cart = cartRepository.findById(user.getCart().getId()).orElse(null);
        OrderEntity order = orderService.createOrder(product, cart, quantity);
        BigDecimal total = cart.getOrders().stream().map(OrderEntity::getTotalPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
        setTotalPrice(cart, total);
    }


    @Override
    public CartEntity createCart(UserEntity user) {
        CartEntity cart = new CartEntity();
        cart.setUser(user);
        cart.setTotalPrice(BigDecimal.valueOf(0));

        cartRepository.save(cart);

        return cart;
    }

    @Override
    public void setTotalPrice(CartEntity cartEntity, BigDecimal total) {
        cartEntity.setTotalPrice(total);
        cartRepository.saveAndFlush(cartEntity);
    }

    @Override
    public void deleteAllOrder(UserEntity user) {
        CartEntity cart = user.getCart();
        cart.setTotalPrice(BigDecimal.valueOf(0));

        for (OrderEntity order : cart.getOrders()) {
            ProductEntity products = order.getProducts();

            products.setQuantity(products.getQuantity() - order.getCount());
            productService.setQuantity(products);

            orderRepository.delete(order);
            cart.setOrders(new ArrayList<>());
        }

        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void deleteOrder(Long id) {
        orderService.deleteOrder(id);
    }

}
