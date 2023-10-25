package com.kemalo.UserService.service.abstracts;

import com.kemalo.UserService.model.User;

public interface UserService {

    User getUserByUsername(String username);
    User addUser(User user);
    User deleteUserByUsername(String username);
    User updateUser(User user);
}
