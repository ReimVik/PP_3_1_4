package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    public User show(long id);

    public void update(User user);

    List<User> index();

    public void deleteById(long id);

    public User findByUsername(String username);
}
