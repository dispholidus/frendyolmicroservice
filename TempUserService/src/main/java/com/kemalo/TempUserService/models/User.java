package com.kemalo.TempUserService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @MongoId
    private String username;

}
