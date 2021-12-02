package com.example.foodshop.model.entity;

import com.example.foodshop.model.enumeration.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private String name;
    private BigDecimal price;
    private String imageUrl;
    private CategoryNameEnum category;
    private String description;

    private Integer quantity;
    private List<CommentEntity> comments;

    public ProductEntity() {
    }

    @Column(nullable = false, unique = true)
    @Size(min = 3)
    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }


    @Column(nullable = false)
    @DecimalMin("0")
    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Lob
    @Size(min = 5)
    public String getImageUrl() {
        return imageUrl;
    }

    public ProductEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Lob
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }



    @Min(0)
    public Integer getQuantity() {
        return quantity;
    }

    public ProductEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public List<CommentEntity> getComments() {
        return comments;
    }

    public ProductEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
