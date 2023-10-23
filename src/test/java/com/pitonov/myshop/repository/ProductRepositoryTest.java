package com.pitonov.myshop.repository;

import com.pitonov.myshop.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    private Product createProduct(){
        Product product = new Product();
        product.setName("Prod");
        product.setDescription("Desc");
        product.setImageUrl("URL");
        product.setPrice(BigDecimal.ZERO);

        return product;
    }

    @Test
    public void testFindProductById(){
        Product product = createProduct();
        productRepository.save(product);

        Product product1 = productRepository.findById(product.getId());

        assertEquals(product.getName(), product1.getName());
    }

    @Test
    public void testFindProductByNameContainingIgnoreCase(){
        Product product = createProduct();
        productRepository.save(product);

        List<Product> productList = productRepository.findByNameContainingIgnoreCase("pro");

        assertEquals(product.getName(), productList.get(0).getName());
    }
}
