package com.example.foodshop.repository;

import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity ,Long> {

    List<ProductEntity> findAllByCategory(CategoryNameEnum category);


}
