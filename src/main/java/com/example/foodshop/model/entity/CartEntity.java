package com.example.foodshop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity {

    private BigDecimal totalPrice;
    private List<OrderEntity> orders = new ArrayList<>();
    private UserEntity user;

    public CartEntity() {
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CartEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }



    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public CartEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }

    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    public UserEntity getUser() {
        return user;
    }

    public CartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
