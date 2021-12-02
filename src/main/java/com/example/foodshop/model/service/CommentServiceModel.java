package com.example.foodshop.model.service;

public class CommentServiceModel {

    private Long productId;
    private String message;
    private String creator;

    public CommentServiceModel() {
    }

    public Long getProductId() {
        return productId;
    }

    public CommentServiceModel setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public CommentServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
