package com.example.pp_3_1_3_bootstrap.controllers;

import com.example.pp_3_1_3_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    public final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Principal principal, Model model) {
        model.addAttribute("user", userService.findByUsername(userService.getCurrentUsername()));
        return "user";
    }
}
