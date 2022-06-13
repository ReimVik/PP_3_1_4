package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User getById(int id);

    User getByUsername(String username);

    List<User> findAll();

    void deleteById(int id);

    Set<Role> getAllRoles();

    void saveUser(User user);

    void updateUser(User updatedUser);
}
