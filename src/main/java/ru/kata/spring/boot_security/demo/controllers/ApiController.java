package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserService userService;

    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/response")
    public ResponseEntity responseEntity() {
        try {
            return ResponseEntity.ok("Server is running!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Problems with Server!");
        }
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.findAll();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.getById(id);
        return user;
    }

    @PostMapping("/users/new")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users/update/{id}")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);
    }

//    @GetMapping
//    public String index(Principal principal,Model model) {
//        User user = userService.findByEmail(principal.getName());
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("user", user);
//        return "index";
//    }
//
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
