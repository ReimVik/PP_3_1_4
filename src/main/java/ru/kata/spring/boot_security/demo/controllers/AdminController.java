package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RoleService roleService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Principal principal,Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new";
    }

    @PostMapping
    public String create(User user,
                         @RequestParam(value = "rolesId", required = false) String[] roles) {
        user.setRoles(roleService.getSetRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-remove/{id}")
    public String remove(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PatchMapping("/user-update")
    public String updateUser(@ModelAttribute() User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}
