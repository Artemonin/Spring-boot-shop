package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface ShoppingCartService {
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearProducts();
    BigDecimal totalPrice();
    Map<Product, Integer> productsInCart();

}
