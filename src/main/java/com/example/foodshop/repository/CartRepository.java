package com.example.foodshop.repository;

import com.example.foodshop.model.entity.CartEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long> {

    void deleteById(Long id);

}
