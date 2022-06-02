package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String index(Principal principal,Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping
    public String create(User user, @RequestParam(value = "rolesId", required = false) String[] roles) {
        user.setRoles(roleService.getSetRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-remove/{id}")
    public String remove(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PatchMapping("/user-update")
    public String updateUser(User user, @RequestParam(value = "rolesId") String[] roles) {
        user.setRoles(roleService.getSetRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }
}
