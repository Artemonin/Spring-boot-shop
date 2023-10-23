package com.pitonov.myshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ControllersTest {
    @Autowired
    AdminController adminController;
    @Autowired
    MainController mainController;
    @Autowired
    LoginController loginController;
    @Autowired
    ProductController productController;
    @Autowired
    RegisterController registerController;
    @Autowired
    ShoppingCartController shoppingCartController;
    @Autowired
    UserController userController;

    @Test
    public void checkIfAdminControllerIsNotNull(){
        assertNotNull(adminController, "AdminController should not be null");
    }

    @Test
    public void checkIfMainControllerIsNotNull(){
        assertNotNull(mainController,"MainController should not be null");
    }

    @Test
    public void checkIfLoginControllerIsNotNull(){
        assertNotNull(loginController,"LoginController should not be null");
    }

    @Test
    public void checkIfProductControllerIsNotNull(){
        assertNotNull(productController,"ProductController should not be null");
    }

    @Test
    public void checkIfRegisterControllerIsNotNull(){
        assertNotNull(registerController,"RegisterController should not be null");
    }

    @Test
    public void checkIfShoppingCartControllerIsNotNull(){
        assertNotNull(shoppingCartController,"ShoppingCartController should not be null");
    }

    @Test
    public void checkIfUserControllerIsNotNull(){
        assertNotNull(userController,"UserController should not be null");
    }
}
