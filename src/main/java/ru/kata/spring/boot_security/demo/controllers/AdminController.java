package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping()
    public String index(Principal principal, Model model) {
        User user = userService.getByUsername(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

//    @GetMapping()
//    public String showAllUsers(Model model, @AuthenticationPrincipal User user) {
//        List<User> users = userService.findAll();
//        Set<Role> listRoles = userService.getAllRoles();
//        model.addAttribute("users", users);
//        model.addAttribute("userObj", new User());
//        model.addAttribute("listRoles", listRoles);
//        model.addAttribute("userRep", userService.getByUsername(user.getUsername()));
//        return "admin";
//    }



//    @PostMapping
//    public String create(User user, @RequestParam(value = "rolesId", required = false) String[] roles) {
//        user.setRoles(roleService.getSetRoles(roles));
//        userService.save(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/user-remove/{id}")
//    public String remove(@PathVariable("id") long id) {
//        userService.deleteById(id);
//        return "redirect:/admin";
//    }
//
//    @PatchMapping("/user-update")
//    public String updateUser(User user, @RequestParam(value = "rolesId") String[] roles) {
//        user.setRoles(roleService.getSetRoles(roles));
//        userService.save(user);
//        return "redirect:/admin";
//    }
}
