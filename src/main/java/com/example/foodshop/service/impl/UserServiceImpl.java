package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.model.service.UserRegistrationServiceModel;
import com.example.foodshop.repository.RoleRepository;
import com.example.foodshop.repository.UserRepository;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;
    private final FoodShoppUserDetailsService foodShoppUserDetailsService;


    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                           CartService cartService, ModelMapper modelMapper, FoodShoppUserDetailsService foodShoppUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.cartService = cartService;
        this.modelMapper = modelMapper;
        this.foodShoppUserDetailsService = foodShoppUserDetailsService;
    }


    @Override
    public void initializeRoles() {
        if (roleRepository.count() == 0) {
            RoleEntity admin = new RoleEntity();
            admin.setRole(RoleNameEnum.ADMIN);

            RoleEntity user = new RoleEntity();
            user.setRole(RoleNameEnum.USER);

            roleRepository.saveAll(List.of(admin, user));

        }
    }


    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        RoleEntity roleEntity = roleRepository.findByRole(RoleNameEnum.USER);


        UserEntity user = new UserEntity();
        user
                .setUsername(userRegistrationServiceModel.getUsername())
                .setFullName(userRegistrationServiceModel.getFullName())
                .setAddress(userRegistrationServiceModel.getAddress())
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setEmail(userRegistrationServiceModel.getEmail())
                .setRoles(Set.of(roleEntity));


        CartEntity cart = cartService.createCart(user);
        user.setCart(cart);
        user = this.userRepository.save(user);

        UserDetails principal = foodShoppUserDetailsService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void initializeAdmin() {
        if (userRepository.count() == 0) {
            RoleEntity adminRole = roleRepository.findByRole(RoleNameEnum.ADMIN);

            UserEntity admin = new UserEntity();

            admin.setUsername("admin")
                    .setPassword(passwordEncoder.encode("1"))
                    .setEmail("vlado@abv.bg")
                    .setAddress("admin123")
                    .setFullName("Admin Admin")
                    .setRoles(Set.of(adminRole));
            CartEntity cart = cartService.createCart(admin);
            admin.setCart(cart);
            userRepository.save(admin);
        }
    }

    @Override
    public UserEntity getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }


}
