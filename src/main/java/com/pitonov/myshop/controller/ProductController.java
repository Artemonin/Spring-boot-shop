package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.Product;
import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.repository.ProductRepository;
import com.pitonov.myshop.service.ProductService;
import com.pitonov.myshop.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductValidator productValidator;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/admin/product/new")
    public String newProduct(Model model){

        model.addAttribute("productForm", new Product());
        model.addAttribute("method", "new");

        return "product";
    }

    @PostMapping("/admin/product/new")
    public String newProduct(@ModelAttribute("productForm") Product product, BindingResult bindingResult, Model model){
        productValidator.validate(product, bindingResult);

        if(bindingResult.hasErrors()){
            log.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method","new");
            return "product";
        }
        productService.save(product);

        return "redirect:/home";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable("id") long productId, Model model){
        Product product = productService.findById(productId);

        if(product != null){
            model.addAttribute("productForm", product);
            model.addAttribute("method", "edit");
            return "product";
        }else {
            return "error/404";
        }
    }

    @PostMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable("id") long productId, @ModelAttribute("productForm") Product product,BindingResult bindingResult, Model model){
        productValidator.validate(product, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("method", "edit");
            return "product";
        }

        productService.update(productId, product);

        return "redirect:/home";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id){
        Product product = productService.findById(id);

        if(product != null){
            productService.delete(id);
            return "redirect:/home";
        }else {
            return "error/404";
        }
    }

    @GetMapping("/product/info/{id}")
    public String productInfo(@PathVariable("id") long id, Model model){
        Product product = productService.findById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        }

        model.addAttribute("product", product);

        return "product-info";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model){
        List<Product> productList = productRepository.findByNameContainingIgnoreCase(keyword);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        }

        model.addAttribute("products", productList);

        return "result";
    }
}
