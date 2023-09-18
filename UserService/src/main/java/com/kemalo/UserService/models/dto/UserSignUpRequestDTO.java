package com.kemalo.UserService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDTO {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
