package com.pitonov.myshop.repository;

import com.pitonov.myshop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public void testFindUserById(){
        User user = createUser();
        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId());

        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    @Transactional
    public void testFindUserByUsername(){
        User user = createUser();
        userRepository.save(user);

        User foundUser = userRepository.findByUsername(user.getUsername());

        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    @Transactional
    public void testFindUserByEmail(){
        User user = createUser();
        userRepository.save(user);

        User foundUser = userRepository.findByEmail(user.getEmail());

        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    public void testFindAllUsersByOrderByIdAsc(){
        User user1 = createUser();
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("Test2");
        user2.setPassword("12345678");
        user2.setEmail("test2@example.com");
        user2.setGender("Male");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAllByOrderByIdAsc();

        assertEquals(2, users.size());
        assertEquals(user1.getUsername(), users.get(0).getUsername());
        assertEquals(user2.getUsername(), users.get(1).getUsername());

    }
}
