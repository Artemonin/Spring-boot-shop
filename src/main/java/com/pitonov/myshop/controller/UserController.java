package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/user")
    public String userPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        return "user";
    }
}
