package com.example.foodshop.service;

import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.service.ProductEditServiceModel;
import com.example.foodshop.model.service.ProductServiceModel;
import com.example.foodshop.model.view.ProductsViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductsViewModel> findAll();

    ProductsViewModel findById(Long id);


    ProductEntity findByIdProduct(Long productId);

    void setQuantity(ProductEntity products);

    List<ProductsViewModel> findAllBioProduct();

    List<ProductsViewModel> findAllMeatProduct();

    List<ProductsViewModel> findAllDrinksProduct();

    List<ProductsViewModel> findAllBreadProduct();

    List<ProductsViewModel> findAllDairyProduct();

    ProductEntity getProduct(Long productId);

    ProductEntity getProductById(Long id);

    List<ProductEntity> findNewTenProduct();

    void deleteProduct(Long id);

    void updateProduct(ProductEditServiceModel productEditServiceModel);
}
