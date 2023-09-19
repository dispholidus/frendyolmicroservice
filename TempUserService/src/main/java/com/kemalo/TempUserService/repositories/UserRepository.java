package com.kemalo.TempUserService.repositories;

import com.kemalo.TempUserService.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
