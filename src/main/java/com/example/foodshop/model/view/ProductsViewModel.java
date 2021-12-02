package com.example.foodshop.model.view;

import com.example.foodshop.model.enumeration.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductsViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Integer quantity;
    private LocalDate addTime;
    private CategoryNameEnum category;
    private String description;


    public ProductsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProductsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductsViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductsViewModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ProductsViewModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public ProductsViewModel setAddTime(LocalDate addTime) {
        this.addTime = addTime;
        return this;
    }
}
