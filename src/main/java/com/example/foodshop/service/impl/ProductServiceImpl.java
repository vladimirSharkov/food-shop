package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import com.example.foodshop.model.service.ProductEditServiceModel;
import com.example.foodshop.model.service.ProductServiceModel;
import com.example.foodshop.model.view.ProductsViewModel;
import com.example.foodshop.repository.ProductRepository;
import com.example.foodshop.service.ProductService;
import com.example.foodshop.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);

        productRepository.save(product);
    }

    @Override
    public List<ProductsViewModel> findAll() {

        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductsViewModel findById(Long id) {

        return productRepository
                .findById(id)
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .orElse(null);
    }

    @Override
    public ProductEntity findByIdProduct(Long productId) {

        return productRepository.findById(productId).orElse(null);
    }

    @Transactional
    @Override
    public void setQuantity(ProductEntity products) {
        productRepository.saveAndFlush(products);
    }

    @Override
    public List<ProductsViewModel> findAllBioProduct() {

        return productRepository
                .findAllByCategory(CategoryNameEnum.BIO)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllMeatProduct() {
        return productRepository
                .findAllByCategory(CategoryNameEnum.MEAT_AND_FISH)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllDrinksProduct() {

        return productRepository
                .findAllByCategory(CategoryNameEnum.DRINKS)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllBreadProduct() {
        return productRepository
                .findAllByCategory(CategoryNameEnum.BREAD_AND_PASTA)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllDairyProduct() {
        return productRepository
                .findAllByCategory(CategoryNameEnum.DAIRY_AND_EGGS)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductEntity> findNewTenProduct() {
        List<ProductEntity> all = productRepository.findAll();
        Collections.reverse(all);
        List<ProductEntity> collect = all.stream().limit(8).collect(Collectors.toList());

        return collect;

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(ProductEditServiceModel productEditServiceModel) {
        ProductEntity product = productRepository.findById(productEditServiceModel.getId()).orElseThrow(()->
                new ObjectNotFoundException("Product with id"+productEditServiceModel.getId()+"not found!"));

        product.setName(productEditServiceModel.getName())
                .setDescription(productEditServiceModel.getDescription())
                .setCategory(productEditServiceModel.getCategory())
                .setPrice(productEditServiceModel.getPrice())
                .setImageUrl(productEditServiceModel.getImageUrl())
                .setQuantity(productEditServiceModel.getQuantity());

        productRepository.save(product);

    }


}
