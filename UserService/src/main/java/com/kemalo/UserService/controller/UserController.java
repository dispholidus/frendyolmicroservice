package com.kemalo.UserService.controller;

import com.kemalo.UserService.models.User;
import com.kemalo.UserService.models.dto.UserSignUpRequestDTO;
import com.kemalo.UserService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody UserSignUpRequestDTO user){
        return userService.signUpUser(user);
    }
    @PostMapping("/exist")
    public UsernamePasswordAuthenticationToken isUserExist(@RequestBody String token){
        return userService.validateUser(token);
    }

}

