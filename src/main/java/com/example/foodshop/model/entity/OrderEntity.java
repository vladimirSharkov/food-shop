package com.example.foodshop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {


    private BigDecimal totalPrice;
    private Integer count;
    private CartEntity cart;
    private ProductEntity products;

    public OrderEntity() {
    }


    public Integer getCount() {
        return count;
    }

    public OrderEntity setCount(Integer quantity) {
        this.count = quantity;
        return this;
    }

    @ManyToOne
    public CartEntity getCart() {
        return cart;
    }

    public OrderEntity setCart(CartEntity cart) {
        this.cart = cart;
        return this;
    }

    @ManyToOne
    public ProductEntity getProducts() {
        return products;
    }

    public OrderEntity setProducts(ProductEntity products) {
        this.products = products;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }


}
