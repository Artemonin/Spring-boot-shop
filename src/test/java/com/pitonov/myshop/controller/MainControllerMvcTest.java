package com.pitonov.myshop.controller;

import com.pitonov.myshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class MainControllerMvcTest {
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @BeforeEach
    public void SetUp(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new MainController(productService)).setViewResolvers(viewResolver).build();
    }

    @Test
    @WithUserDetails("TestUser")
    public void testHomeView() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    @WithUserDetails("TestUser")
    public void testHomeView2() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }


    @Test
    @WithUserDetails("TestUser")
    public void testModelAttributes() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products","user"))
                .andDo(print());
    }

//    @Test
//    public void testAuthenticatedUser(){
//        mockMvc.perform(get("/home").with(WithUserDetails("TestUser")))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("user"));
//    }
}
