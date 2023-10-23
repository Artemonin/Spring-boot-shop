package com.pitonov.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error){
        if (error != null)
            model.addAttribute("error","Email or password is wrong");

        return "login";
    }
}
