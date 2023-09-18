package com.kemalo.UserService.mapper;

import com.kemalo.UserService.models.User;
import com.kemalo.UserService.models.dto.UserSignUpRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserDTOMapper {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    public User userSignUpRequestDTOtoUser(UserSignUpRequestDTO userSignUpRequestDTO){
        User  newUser = new User();
        newUser.setUsername(userSignUpRequestDTO.getUsername());
        newUser.setPassword(encoder.encode(userSignUpRequestDTO.getPassword()));
        newUser.setRole("ROLE_USER");
        return newUser;
    }
}
