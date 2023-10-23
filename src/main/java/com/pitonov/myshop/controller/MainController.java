package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.Product;
import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    private List<Product> getAllProducts(){
        return productService.findAllByOrderByIdAsc();
    }


    @GetMapping(value = {"/","/home"})
    public String home(Model model){
        model.addAttribute("products", getAllProducts());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        }
        return "home";
    }


}
