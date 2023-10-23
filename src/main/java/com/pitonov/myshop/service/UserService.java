package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.entity.enums.Role;

import java.util.List;

public interface UserService {
    void save(User user);
    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);
    void login(String username, String password);
    void changeUserRoles(User user, Role role);
    List<User> getAllUsers();
}
