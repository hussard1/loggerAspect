package com.hussard01.application.controller;

import com.hussard01.application.model.CommonResponse;
import com.hussard01.application.model.UserRequest;
import com.hussard01.application.model.UserResponse;
import com.hussard01.application.service.UserService;
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
    public CommonResponse<UserResponse> get(@Min(1) @PathVariable long id) throws Exception {
        return CommonResponse.of(userService.getUser(id));
    }

    @PostMapping
    public CommonResponse<UserResponse> save(@Validated @RequestBody UserRequest userRequest) {
        return CommonResponse.of(userService.save(userRequest));
    }
}
