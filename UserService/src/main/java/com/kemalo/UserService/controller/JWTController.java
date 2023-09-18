package com.kemalo.UserService.controller;

import com.kemalo.UserService.models.dto.JWTAuthenticationRequest;
import com.kemalo.UserService.models.dto.JWTToken;
import com.kemalo.UserService.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authenticate")
public class JWTController {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public JWTToken getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword()));
        if (authentication.isAuthenticated()){
            return new JWTToken(jwtService.generateToken(authRequest.getUserName()));
        }
        else {
            throw new RuntimeException("Invalid user credentials");
        }
    }

}
