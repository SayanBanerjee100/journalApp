package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.model.User;
import com.xyz.JournalApp1.journal.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createEntry(user);
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllEntries();
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getEntryById(id);
    }
}
