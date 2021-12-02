package com.example.foodshop.web;


import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "carts")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }
    @Transactional
    @GetMapping("/add")
    public String add(Model model, Principal principal) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        CartEntity cart = user.getCart();
        BigDecimal total = cart.getOrders().stream().map(OrderEntity::getTotalPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);

        cartService.setTotalPrice(cart, total);

        List<OrderEntity> orders = cart.getOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("cart", cart);
        BigDecimal totalPrice = cart.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    @Transactional
    @GetMapping("/buy")
    public String deleteOrder(Principal principal) {
        UserEntity user = userService.getUserByUsername(principal.getName());
        cartService.deleteAllOrder(user);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductToCart(@PathVariable Long id, HttpServletRequest request) {
        String referent = request.getHeader("Referer");

        cartService.deleteOrder(id);
        return "redirect:" + referent;
    }

}
