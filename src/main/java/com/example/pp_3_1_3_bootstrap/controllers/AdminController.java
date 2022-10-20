package com.example.pp_3_1_3_bootstrap.controllers;

import com.example.pp_3_1_3_bootstrap.models.Role;
import com.example.pp_3_1_3_bootstrap.models.User;
import com.example.pp_3_1_3_bootstrap.service.RoleService;
import com.example.pp_3_1_3_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers (Principal principal, Model model) {
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("admin", userService.findByUsername(principal.getName()));
        model.addAttribute("roles", roleService.getRoleList());
        return "/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser (@PathVariable ("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/update/{id}")
    public String update
            (@ModelAttribute("user") User user, @PathVariable("id") long id, @RequestParam(name="roles", required = false) String[] roles) {
        List<Role> roles1 = new ArrayList<>();
        if(roles == null) {
            user.setRoles((List<Role>) userService.findOne(id).getRoles());
        } else {
            for (String role: roles) {
                roles1.add(roleService.getRoleById(id));
                user.setRoles(roles1);
            }
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping ("/registration")
    public String addUser (@ModelAttribute ("user") User user, @RequestParam ("roles") List<String> role) {
        user.setRoles(userService.getSetOfRoles(role));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
