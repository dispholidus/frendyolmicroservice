package com.kemalo.UserService.controller;

import com.kemalo.UserService.model.User;
import com.kemalo.UserService.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @GetMapping()
    public List<User> getUser(){
        return userService.getUsers();
    }
    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @DeleteMapping("/{username}")
    public User deleteUserByUsername(@PathVariable String username){
        return userService.deleteUserByUsername(username);
    }
}
