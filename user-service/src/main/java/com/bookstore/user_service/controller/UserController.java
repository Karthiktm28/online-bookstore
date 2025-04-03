package com.bookstore.user_service.controller;

import com.bookstore.user_service.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User("1", "Alice", "alice@example.com"));
        users.add(new User("2", "Bob", "bob@example.com"));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        users.add(user);
        return user;
    }
}
