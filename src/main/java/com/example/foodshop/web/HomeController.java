package com.example.foodshop.web;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.service.ProductService;
import com.example.foodshop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal, Authentication authentication) {
        if (authentication == null) {
            return "index";
        }

        List<ProductEntity> newTenProduct = productService.findNewTenProduct();

        UserEntity user = userService.getUserByUsername(principal.getName());
        CartEntity cart = user.getCart();
        BigDecimal totalPrice = cart.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("newTenProduct", newTenProduct);

        return "index";
    }
}
