package com.hussard01.domain.user.service;

import com.hussard01.application.user.model.UserResponse;
import com.hussard01.domain.user.entity.User;
import com.hussard01.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
