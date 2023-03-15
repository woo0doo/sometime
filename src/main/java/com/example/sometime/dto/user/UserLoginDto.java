package com.example.sometime.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserLoginDto {
    private String email;
    private String password;

    public UserLoginDto(@JsonProperty("email") String email,@JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
