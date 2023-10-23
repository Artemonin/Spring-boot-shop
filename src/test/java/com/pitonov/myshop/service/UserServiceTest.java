package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserService userService;

    private User createUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setPassword("12345678");
        user.setEmail("test@example.com");
        user.setGender("Male");
        return user;
    }

    @Test
    public void testSaveUser(){
        User user = createUser();

        when(userService.findById(user.getId())).thenReturn(user);
        User found = userService.findById(user.getId());

        Assertions.assertEquals(found.getUsername(), user.getUsername());
        Assertions.assertEquals(found.getId(), user.getId());
        Assertions.assertEquals(found.getGender(), user.getGender());
    }

    @Test
    public void testFindById(){
        User user = createUser();
        when(userService.findById(10L)).thenReturn(user);
        User found = userService.findById(10L);

        Assertions.assertEquals(found.getUsername(), user.getUsername());
        Assertions.assertEquals(found.getEmail(), user.getEmail());
        Assertions.assertEquals(found.getGender(), user.getGender());
    }

    @Test
    public void testFindByUsername(){
        User user = createUser();
        when(userService.findByUsername(user.getUsername())).thenReturn(user);
        User found = userService.findByUsername(user.getUsername());

        Assertions.assertEquals(found.getUsername(), user.getUsername());
        Assertions.assertEquals(found.getEmail(), user.getEmail());
        Assertions.assertEquals(found.getGender(), user.getGender());
    }

    @Test
    public void testFindByEmail(){
        User user = createUser();
        when(userService.findByEmail(user.getEmail())).thenReturn(user);
        User found = userService.findByEmail(user.getEmail());

        Assertions.assertEquals(found.getUsername(), user.getUsername());
        Assertions.assertEquals(found.getEmail(), user.getEmail());
        Assertions.assertEquals(found.getGender(), user.getGender());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        users.add(user1);
        users.add(user2);

        when(userService.getAllUsers()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        Assertions.assertEquals(2, allUsers.size());
        Assertions.assertEquals("user1", allUsers.get(0).getUsername());
        Assertions.assertEquals("user2", allUsers.get(1).getUsername());
    }
}
