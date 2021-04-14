package com.hussard01.application.service;

import com.hussard01.application.model.UserRequest;
import com.hussard01.application.model.UserResponse;
import com.hussard01.domain.service.UserDomainService;
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
