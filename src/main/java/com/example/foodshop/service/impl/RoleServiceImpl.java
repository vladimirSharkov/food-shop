package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.repository.RoleRepository;
import com.example.foodshop.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<RoleEntity> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Set<RoleEntity> findAllByRoleName(RoleNameEnum roleNameEnum) {
        return roleRepository.findAllByRole(roleNameEnum);
    }
}
