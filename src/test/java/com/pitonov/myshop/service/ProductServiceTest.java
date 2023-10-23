package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {
    @MockBean
    private ProductService productService;

    private Product createProduct(){
        Product product = new Product();
        product.setName("Prod");
        product.setDescription("Desc");
        product.setImageUrl("URL");
        product.setPrice(BigDecimal.ZERO);

        return product;
    }


    @Test
    public void testSaveProduct(){
        Product product = createProduct();
        productService.save(product);
        when(productService.findById(product.getId())).thenReturn(product);
        Product found = productService.findById(product.getId());

        Assertions.assertEquals(found.getId(),product.getId());
        Assertions.assertEquals(found.getName(),product.getName());
        Assertions.assertEquals(found.getDescription(),product.getDescription());
        Assertions.assertEquals(found.getPrice(),product.getPrice());
        Assertions.assertEquals(found.getImageUrl(),product.getImageUrl());
    }

    @Test
    public void testUpateProduct(){
        Product product = createProduct();
        Product newProduct = createProduct();
        newProduct.setName("NewName");
        newProduct.setPrice(BigDecimal.valueOf(500));
        newProduct.setDescription("New");

        productService.save(product);
        productService.update(product.getId(), newProduct);

        when(productService.findById(product.getId())).thenReturn(newProduct);
        Product found = productService.findById(newProduct.getId());

        Assertions.assertEquals(found.getPrice(),BigDecimal.valueOf(500));
        Assertions.assertEquals(found.getName(),"NewName");
        Assertions.assertEquals(found.getDescription(),"New");
    }

    @Test
    public void testDeleteProduct(){
        Product product = createProduct();
        productService.save(product);
        productService.delete(product.getId());

        Product found = productService.findById(product.getId());

        Assertions.assertNull(found);
    }

    @Test
    public void testFindProductById(){
        Product product = createProduct();
        when(productService.findById(10L)).thenReturn(product);
        Product found = productService.findById(10L);

        Assertions.assertEquals(found.getName(), product.getName());
        Assertions.assertEquals(found.getDescription(), product.getDescription());
        Assertions.assertEquals(found.getImageUrl(), product.getImageUrl());
        Assertions.assertEquals(found.getName(), product.getName());
        Assertions.assertEquals(found.getPrice(), product.getPrice());
    }

    @Test
    public void whenFindAllByOrderByIdAscThenReturnAllProducts(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Product1");
        Product product2 = new Product();
        product2.setName("Product2");

        products.add(product1);
        products.add(product2);

        when(productService.findAllByOrderByIdAsc()).thenReturn(products);

        List<Product> foundProducts = productService.findAllByOrderByIdAsc();

        Assertions.assertEquals(2, foundProducts.size());
        Assertions.assertEquals("Product1", foundProducts.get(0).getName());
        Assertions.assertEquals("Product2", foundProducts.get(1).getName());
    }

}
