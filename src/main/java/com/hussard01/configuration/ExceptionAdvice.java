package com.hussard01.configuration;

import com.hussard01.application.enums.ErrorCode;
import com.hussard01.application.model.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        return ResponseEntity.badRequest().body(CommonResponse.of400Error(ErrorCode.ILLEGAL_ARGUMENT, exception.getMessage()));
    }
}
