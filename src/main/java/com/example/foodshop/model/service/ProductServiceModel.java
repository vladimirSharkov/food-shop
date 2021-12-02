package com.example.foodshop.model.service;

import com.example.foodshop.model.enumeration.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private CategoryNameEnum category;
    private String description;
    private LocalDate addTime;
    private Integer quantity;


    public ProductServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public ProductServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ProductServiceModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public ProductServiceModel setAddTime(LocalDate addTime) {
        this.addTime = addTime;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductServiceModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
