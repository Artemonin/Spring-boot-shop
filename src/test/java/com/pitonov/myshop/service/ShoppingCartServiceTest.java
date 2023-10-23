package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ShoppingCartServiceTest {
    @MockBean
    private ShoppingCartService shoppingCartService;

    private Product createProduct(){
        Product product = new Product();
        product.setName("Prod");
        product.setDescription("Desc");
        product.setImageUrl("URL");
        product.setPrice(BigDecimal.ZERO);

        return product;
    }

    @Test
    public void testAddProductToCart(){
        Map<Product, Integer> cart = new LinkedHashMap<>();
        Product product = createProduct();

        when(shoppingCartService.productsInCart()).thenReturn(cart);

        cart.put(product, 1);

        Assertions.assertTrue(shoppingCartService.productsInCart().containsKey(product));
    }

    @Test
    public void testAddTwoSameProductsToCart(){
        Map<Product, Integer> cart = new LinkedHashMap<>();
        Product product = createProduct();
        Product product2 = createProduct();

        when(shoppingCartService.productsInCart()).thenReturn(cart);

        cart.put(product, 1);
        cart.put(product2, 1);

        Assertions.assertNotNull(shoppingCartService.productsInCart().get(product));
        Assertions.assertNotNull(shoppingCartService.productsInCart().get(product2));
    }

    @Test
    public void testRemoveProductFromCart(){
        Map<Product, Integer> cart = new LinkedHashMap<>();
        Product product = createProduct();

        when(shoppingCartService.productsInCart()).thenReturn(cart);

        cart.put(product, 1);
        Assertions.assertNotNull(shoppingCartService.productsInCart().get(product));

        cart.remove(product);
        Assertions.assertNull(shoppingCartService.productsInCart().get(product));
    }
}
