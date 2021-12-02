package com.example.foodshop.repository;


import com.example.foodshop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Modifying
    @Transactional
    @Query("delete from OrderEntity as o where o.id = :id")
    void deleteOrderById(@Param("id") Long id);
}
