package com.example.testfive.controller;

import com.example.testfive.domain.User;
import com.example.testfive.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{username}")
    public User findUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.createUser(user.getUsername());
    }

    @GetMapping("/byPattern/{username}")
    public Set<String> findAllByPattern(@PathVariable String username) {
        Set<String> usernames = new HashSet<>();
        for (User user : userService.findAllByPattern(username)) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

}
