package com.hussard01.application.user.controller;

import com.hussard01.application.user.model.UserRequest;
import com.hussard01.application.user.model.UserResponse;
import com.hussard01.application.user.service.UserService;
import com.hussard01.configuration.LoggerExecutionTime;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @LoggerExecutionTime
    public UserResponse get(@Min(1) @PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserResponse save(@Validated @RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }
}
