package com.kemalo.UserService;

public interface UserService {

    User getUserByUsername(String username);
    User addUser(User user);
    User deleteUserByUsername(String username);
    User updateUser(User user);
}
