package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.model.mongo.User;
import com.xyz.JournalApp1.journal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "User management APIs")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
