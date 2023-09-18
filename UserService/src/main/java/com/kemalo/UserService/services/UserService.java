package com.kemalo.UserService.services;

import com.kemalo.UserService.mapper.UserDTOMapper;
import com.kemalo.UserService.models.User;
import com.kemalo.UserService.models.dto.UserSignUpRequestDTO;
import com.kemalo.UserService.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public  class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final UserDTOMapper mapper;
    public UsernamePasswordAuthenticationToken validateUser(String token) {
        String username = jwtService.extractUsernameFromToken(token);
        if (userRepository.existsById(username)){
            UserDetails userDetails = this.loadUserByUsername(username);
            System.out.println(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()).toString());
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        throw new RuntimeException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            return user.get();
        }
        throw new RuntimeException();
    }

    public User signUpUser(UserSignUpRequestDTO user) {

        return userRepository.save(mapper.userSignUpRequestDTOtoUser(user));
    }
}
