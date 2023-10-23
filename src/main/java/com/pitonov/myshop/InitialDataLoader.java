package com.pitonov.myshop;

import com.pitonov.myshop.entity.Product;
import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.entity.enums.Role;
import com.pitonov.myshop.service.ProductService;
import com.pitonov.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public InitialDataLoader(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }


    @Override
    public void run(String... args) throws Exception {
        createProducts();
        createUsers();
    }

    private void createUsers(){
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("12345678");
        user.setGender("Male");
        user.setEmail("testmail@gmail.com");
        user.getRoles().add(Role.ROLE_USER);
        user.setBalance(BigDecimal.TEN);

        User user2 = new User();
        user2.setUsername("TestUser2");
        user2.setPassword("foradmin");
        user2.setGender("Female");
        user2.setEmail("testmail2@gmail.com");
        user2.getRoles().add(Role.ROLE_ADMIN);
        user2.setBalance(BigDecimal.ZERO);

        userService.save(user);
        userService.save(user2);
    }

    private void createProducts(){
        final String DESCRIPTION = "Example Description";
        final BigDecimal PRICE = BigDecimal.valueOf(10);

        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        product.setName("Product 1");
        product.setDescription(DESCRIPTION);
        product.setImageUrl("https://prostyle.in.ua/Media/static/article/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8_1.jpg");
        product.setPrice(PRICE);

        product1.setName("Product 2");
        product1.setDescription(DESCRIPTION);
        product1.setImageUrl("https://prostyle.in.ua/Media/static/article/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8_1.jpg");
        product1.setPrice(PRICE);

        product2.setName("Product 3");
        product2.setDescription(DESCRIPTION);
        product2.setImageUrl("https://prostyle.in.ua/Media/static/article/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8_1.jpg");
        product2.setPrice(PRICE);

        product3.setName("Product 4");
        product3.setDescription(DESCRIPTION);
        product3.setImageUrl("https://prostyle.in.ua/Media/static/article/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8_1.jpg");
        product3.setPrice(PRICE);

        product4.setName("Product 5");
        product4.setDescription(DESCRIPTION);
        product4.setImageUrl("https://prostyle.in.ua/Media/static/article/%D0%BA%D1%80%D0%BE%D1%81%D1%81%D0%BE%D0%B2%D0%BA%D0%B8_1.jpg");
        product4.setPrice(PRICE);

        productService.save(product);
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
    }


}
