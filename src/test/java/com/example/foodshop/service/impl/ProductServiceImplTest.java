package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import com.example.foodshop.model.service.ProductServiceModel;
import com.example.foodshop.model.view.ProductsViewModel;
import com.example.foodshop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private ProductServiceImpl serviceToTest;
    private ProductServiceModel productServiceModel;
    private ProductsViewModel productsViewModel;
    private List<ProductsViewModel> list = new ArrayList<>();
    private ProductEntity product, product1,product2;

    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void init() {
        serviceToTest = new ProductServiceImpl(mockModelMapper, mockProductRepository);
        productsViewModel = new ProductsViewModel();
        productsViewModel.setAddTime(LocalDate.now()).setCategory(CategoryNameEnum.BIO).setName("tea")
                .setDescription("dada").setQuantity(2).setImageUrl("image").setPrice(BigDecimal.valueOf(1))
                .setId(1L);

        list.add(productsViewModel);

        product = new ProductEntity();
        product.setCategory(CategoryNameEnum.BIO).setName("tea")
                .setDescription("dada").setQuantity(2).setImageUrl("image").setPrice(BigDecimal.valueOf(1))
                .setId(1L);

        product1 = new ProductEntity();
        product1.setCategory(CategoryNameEnum.BIO).setName("tea")
                .setDescription("dada").setQuantity(2).setImageUrl("image").setPrice(BigDecimal.valueOf(1))
                .setId(2L);
        product2 = new ProductEntity();
        product2.setCategory(CategoryNameEnum.MEAT_AND_FISH).setName("tea")
                .setDescription("dada").setQuantity(2).setImageUrl("image").setPrice(BigDecimal.valueOf(1))
                .setId(2L);


    }

    @Test
    void testFindAll() {
        when(mockProductRepository.findAll()).thenReturn(List.of(product, product1));

        List<ProductsViewModel> all = serviceToTest.findAll();


        Assertions.assertEquals(2, all.size());

    }

    @Test
    void testFindAllBioProduct() {

        when(mockProductRepository.findAllByCategory(CategoryNameEnum.BIO)).thenReturn(List.of(product, product1));

        List<ProductsViewModel> allBioProduct = serviceToTest.findAllBioProduct();

        Assertions.assertEquals(2,allBioProduct.size());
    }




    @Test
    void testGetProductById() {
       when(mockProductRepository.findById(2L)).thenReturn(Optional.of(product1));
        ProductEntity byIdProduct = serviceToTest.findByIdProduct(2L);
        Assertions.assertEquals(product1,byIdProduct);
    }



}