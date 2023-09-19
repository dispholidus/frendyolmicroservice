package com.kemalo.TempUserService.controllers;

import com.kemalo.TempUserService.models.User;
import com.kemalo.TempUserService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/{username}")
    public String getUserById(@PathVariable String username){
        return userService.getUserById(username).getUsername();
    }
    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userService.addUser(user.getUsername());
    }
    @DeleteMapping("/user/{username}")
    public String deleteUserById(@PathVariable String username){
        return userService.deleteUserById(username);
    }
}
