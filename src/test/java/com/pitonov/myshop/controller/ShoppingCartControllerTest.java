package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.Product;
import com.pitonov.myshop.service.ProductService;
import com.pitonov.myshop.service.ShoppingCartService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class ShoppingCartControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setUp(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new ShoppingCartController(productService, shoppingCartService)).setViewResolvers(viewResolver).build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("TestUser", null);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testShoppingCartView() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andDo(print());
    }

    @Test
    public void testAddToCart() throws Exception {
        long productId = 1L;
        Product product = new Product();
        when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/cart/add/{id}",productId)
                .with(user("TestUser")
                        .authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));

        verify(shoppingCartService, times(1)).addProduct(product);

    }

    @Test
    public void testRemoveFromCart() throws Exception {
        long productId = 1L;
        Product product = new Product();

        when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/cart/remove/{id}", productId)
                .with(user("TestUser")
                        .authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/cart"));

        verify(shoppingCartService, times(1)).removeProduct(product);
    }
}
