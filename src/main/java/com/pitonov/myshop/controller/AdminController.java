package com.pitonov.myshop.controller;

import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.entity.enums.Role;
import com.pitonov.myshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String adminProfile(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        if(user!=null){
            model.addAttribute("user", user);
        }
        return "admin";
    }

    @GetMapping("/admin/manage-users")
    public String manageUsers(Model model){
        List<User> userList = userService.getAllUsers();
        List<Role> roles = List.of(Role.values());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        }

        model.addAttribute("users",userList);
        model.addAttribute("roles",roles);

        return "manage-users";
    }

    @PostMapping("/admin/assign-role")
    public String assignRoles(@RequestParam("userId") Long userId, @RequestParam("role") String roleName){
        User user = userService.findById(userId);
        Role role = Role.valueOf(roleName);
        userService.changeUserRoles(user,role);

        return "redirect:/admin/manage-users";
    }

}
