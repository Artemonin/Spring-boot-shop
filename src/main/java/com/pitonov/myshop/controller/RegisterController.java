package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.entity.enums.Role;
import com.pitonov.myshop.service.UserService;
import com.pitonov.myshop.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "register";
        }

        userForm.setBalance(BigDecimal.ZERO);
        userForm.getRoles().add(Role.ROLE_USER);
        userService.save(userForm);
        userService.login(userForm.getUsername(), userForm.getPassword());

        return "redirect:/home";
    }
}
