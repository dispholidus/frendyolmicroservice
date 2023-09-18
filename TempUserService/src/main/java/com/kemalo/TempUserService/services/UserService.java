package com.kemalo.TempUserService.services;

import com.kemalo.TempUserService.models.User;
import com.kemalo.TempUserService.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(String username){
        return userRepository.findById(username).get();
    }
    public User addUser(String username){
        User user = new User(username);
        return userRepository.save(user);
    }
}
