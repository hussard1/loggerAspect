package com.hussard01.application.user.service;

import com.hussard01.application.user.model.UserRequest;
import com.hussard01.application.user.model.UserResponse;
import com.hussard01.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;

    public UserResponse getUser(final long id) {
        return userDomainService.getUser(id)
                .map(UserResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저 아이디 입니다."));
    }

    public UserResponse save(UserRequest userRequest) {
        return UserResponse.of(userDomainService.save(userRequest.toEntity()));
    }
}
