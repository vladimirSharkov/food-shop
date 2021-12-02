package com.example.foodshop.repository;

import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity ,Long> {

    RoleEntity findByRole(RoleNameEnum roleNameEnum);
    Set<RoleEntity> findAllByRole(RoleNameEnum role);
}
