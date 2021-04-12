package com.hussard01.application.user.model;

import com.hussard01.domain.user.entity.User;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class UserRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
