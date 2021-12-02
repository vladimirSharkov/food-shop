package com.example.foodshop.web;

import com.example.foodshop.model.binding.AddProductBindingModel;
import com.example.foodshop.model.binding.BuyProductBindingModel;
import com.example.foodshop.model.binding.ProductEditBindingModel;
import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.service.ProductEditServiceModel;
import com.example.foodshop.model.service.ProductServiceModel;
import com.example.foodshop.model.view.ProductsViewModel;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import com.example.foodshop.service.UserService;
import com.example.foodshop.service.impl.FoodShoppUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CartService cartService;


    public ProductController(ProductService productService, ModelMapper modelMapper,
                             UserService userService, CartService cartService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.cartService = cartService;

    }


    @ModelAttribute
    AddProductBindingModel addProductBindingModel() {
        return new AddProductBindingModel();
    }

    @ModelAttribute
    BuyProductBindingModel buyProductBindingModel() {
        return new BuyProductBindingModel();
    }

    @GetMapping("/all")
    public String products(Model model, Principal principal) {

        List<ProductsViewModel> productsView = productService.findAll();
        model.addAttribute("productsView", productsView);
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }

        return "product";
    }

    @GetMapping("/bio")
    public String bioProduct(Model model, Principal principal) {

        List<ProductsViewModel> allBioProduct = productService.findAllBioProduct();
        model.addAttribute("bioProduct", allBioProduct);
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "bio-product";
    }

    @GetMapping("meat")
    public String meatProduct(Model model, Principal principal) {

        List<ProductsViewModel> allMeaProduct = productService.findAllMeatProduct();
        model.addAttribute("meatProduct", allMeaProduct);
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "meat-product";
    }

    @GetMapping("drinks")
    public String drinksProduct(Model model, Principal principal) {

        List<ProductsViewModel> allDrinksProduct = productService.findAllDrinksProduct();
        model.addAttribute("drinksProduct", allDrinksProduct);
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "drinks-product";
    }

    @GetMapping("bread")
    public String breadProduct(Model model, Principal principal) {

        List<ProductsViewModel> allBreadProduct = productService.findAllBreadProduct();
        model.addAttribute("breadProduct", allBreadProduct);
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "bread-product";
    }

    @GetMapping("dairy")
    public String dairyProduct(Model model, Principal principal) {

        List<ProductsViewModel> allDairyProduct = productService.findAllDairyProduct();
        model.addAttribute("dairyProduct", allDairyProduct);
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "dairy-product";
    }


    @GetMapping("add")
    public String add() {
        return "add-product";
    }

    @PostMapping("add")
    public String adminAddProductPost(@Valid AddProductBindingModel addProductBindingModel, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addProductBindingModel", addProductBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel"
                            , bindingResult);
            return "redirect:add";
        }
        productService.addProduct(modelMapper.map(addProductBindingModel, ProductServiceModel.class));

        return "redirect:all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("productDetails", productService.findById(id));
        if (principal != null) {
            UserEntity user = userService.getUserByUsername(principal.getName());
            CartEntity cart = user.getCart();
            BigDecimal totalPrice = cart.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "details";
    }

    @GetMapping("/cartInfo")
    public String cartInfo() {
        return "cart";
    }

    @PostMapping("/addProduct/{id}")
    public String addProductToCart(@Valid BuyProductBindingModel buyProductBindingModel, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes, @PathVariable Long id,
                                   Principal principal, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("buyProductBindingModel", buyProductBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.buyProductBindingModel"
                            , bindingResult);
            return "redirect:all";
        }

        UserEntity user = userService.getUserByUsername(principal.getName());
        ProductEntity product = productService.getProductById(id);
        cartService.productAddToCart(user, product, buyProductBindingModel.getQuantity());
        return "redirect:/carts/add";

    }

    @GetMapping("/edit")
    public String editDeleteProduct(Model model) {
        List<ProductsViewModel> all = productService.findAll();
        model.addAttribute("allProduct", all);
        return "edit";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        String referent = request.getHeader("Referer");
        productService.deleteProduct(id);

        return "redirect:"+referent;
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id ,Model model){
        ProductsViewModel productsViewModel = productService.findById(id);
        ProductEditBindingModel productEditBindingModel = modelMapper
                .map(productsViewModel, ProductEditBindingModel.class);
        model.addAttribute("productEditBindingModel",productEditBindingModel);
        return "update";
    }

    @PatchMapping("/edit/{id}")
    public String editProductPatch(@PathVariable Long id,@Valid ProductEditBindingModel productEditBindingModel,
                                   BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productEditBindingModel",productEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productEditBindingModel",bindingResult);
            return "redirect:/edit"+id;
        }
        ProductEditServiceModel productEditServiceModel = modelMapper.map(productEditBindingModel, ProductEditServiceModel.class);
        productService.updateProduct(productEditServiceModel);

        return "redirect:/product/edit";
    }
}
