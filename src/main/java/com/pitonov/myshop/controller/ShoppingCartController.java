package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.Product;
import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.service.ProductService;
import com.pitonov.myshop.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String cart(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        }

        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") long id){
        Product product = productService.findById(id);

        if(product!=null){
            shoppingCartService.addProduct(product);
        }

        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") long id){
        Product product = productService.findById(id);

        if(product!=null){
            shoppingCartService.removeProduct(product);
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearCart(){
        shoppingCartService.clearProducts();

        return "redirect:/cart";
    }
}
