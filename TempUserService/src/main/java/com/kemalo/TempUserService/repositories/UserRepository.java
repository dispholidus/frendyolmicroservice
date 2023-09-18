package com.kemalo.TempUserService.repositories;

import com.kemalo.TempUserService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
