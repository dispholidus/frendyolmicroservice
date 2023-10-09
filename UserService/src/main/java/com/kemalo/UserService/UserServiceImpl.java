package com.kemalo.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent())
            return user.get();
        throw new RuntimeException("User not found");
    }

    @Override
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteUserByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            userRepository.deleteById(username);
            return user.get();
        }
        throw new RuntimeException("User not exist");
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User updatedUser = userRepository.findById(user.getUsername()).get();
        updatedUser.setUsername(
                (user.getUsername()==null || user.getUsername().isEmpty()) ? updatedUser.getUsername() : user.getUsername());
            return userRepository.save(updatedUser);

    }
}
