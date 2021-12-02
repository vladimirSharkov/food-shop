package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.model.view.ProductsViewModel;
import com.example.foodshop.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    private RoleServiceImpl serviceToTest;
    private RoleEntity adminRole, userRole;
    private Set<RoleEntity> roleEntitySet = new HashSet<>();
    private UserEntity testUser;

    @Mock
    private RoleRepository mockRoleRepository;

    @BeforeEach
    void init() {
        serviceToTest = new RoleServiceImpl(mockRoleRepository);

        adminRole = new RoleEntity();
        adminRole.setRole(RoleNameEnum.ADMIN);

        userRole = new RoleEntity();
        userRole.setRole(RoleNameEnum.USER);

        roleEntitySet.add(adminRole);
        roleEntitySet.add(userRole);

        testUser = new UserEntity();
        testUser.setUsername("Vlado").setEmail("vlado@vlado.com").setAddress("vlado132").setFullName("VLado Sharkov")
                .setPassword("12345").setRoles(Set.of(userRole));
    }

    @Test
    void testFindAllByRoleName() {
        Mockito
                .when(serviceToTest.findAllByRoleName(RoleNameEnum.USER))
                .thenReturn(Set.of(userRole));

        Set<RoleEntity> byRole = mockRoleRepository.findAllByRole(RoleNameEnum.USER);

        assertNotNull(byRole);


    }

    @Test
    void testFindAll() {
        when(mockRoleRepository.findAll()).thenReturn(List.of(adminRole, userRole));
        Set<RoleEntity> all = serviceToTest.findAll();


        assertNotNull(all);
        Assertions.assertEquals(2, all.size());


    }

}