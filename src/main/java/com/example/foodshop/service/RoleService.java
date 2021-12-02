package com.example.foodshop.service;

import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;

import java.util.Set;

public interface RoleService {
    Set<RoleEntity> findAll();

    Set<RoleEntity> findAllByRoleName(RoleNameEnum roleNameEnum);
}
