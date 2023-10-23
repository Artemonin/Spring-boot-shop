package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Map<Product, Integer> shoppingCart = new LinkedHashMap<>();

    @Override
    public void addProduct(Product product) {
        if(shoppingCart.containsKey(product)){
            shoppingCart.replace(product, shoppingCart.get(product) + 1);
        }else {
            shoppingCart.put(product,1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if(shoppingCart.containsKey(product)){
            if(shoppingCart.get(product) > 1){
                shoppingCart.replace(product, shoppingCart.get(product)-1);
            }else if(shoppingCart.get(product) == 1){
                shoppingCart.remove(product);
            }
        }
    }

    @Override
    public void clearProducts() {
        shoppingCart.clear();
    }

    @Override
    public BigDecimal totalPrice() {
        return shoppingCart.entrySet().stream()
                .map(k->k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public Map<Product,Integer> productsInCart() {
        return Collections.unmodifiableMap(shoppingCart);
    }
}
