package com.kemalo.UserService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JWTAuthenticationRequest {
    @JsonProperty("userId")
    private String userName;
    @JsonProperty("userPassword")
    private String userPassword;
}

