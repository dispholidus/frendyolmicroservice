package com.kemalo.UserService.repositories;

import com.kemalo.UserService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
