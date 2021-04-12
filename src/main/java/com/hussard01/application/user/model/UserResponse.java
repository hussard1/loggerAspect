package com.hussard01.application.user.model;

import com.hussard01.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private final long id;
    private final  String username;
    private final  String password;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
