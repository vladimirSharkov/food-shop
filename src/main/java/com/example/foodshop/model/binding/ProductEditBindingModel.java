package com.example.foodshop.model.binding;

import com.example.foodshop.model.enumeration.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductEditBindingModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private CategoryNameEnum category;
    private String description;
    private LocalDate addTime;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public ProductEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEditBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ProductEditBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public ProductEditBindingModel setAddTime(LocalDate addTime) {
        this.addTime = addTime;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductEditBindingModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
