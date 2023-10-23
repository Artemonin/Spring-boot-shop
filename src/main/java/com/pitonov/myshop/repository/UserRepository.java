package com.pitonov.myshop.repository;

import com.pitonov.myshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllByOrderByIdAsc();
}
