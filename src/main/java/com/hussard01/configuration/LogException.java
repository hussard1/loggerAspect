package com.hussard01.configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

@Builder
@ToString
@Getter
public class LogException {

    private final String className;
    private final String methodName;
    private final String message;
    private final String exception;

    public static LogException create(JoinPoint joinPoint, Throwable ex) {

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return LogException.builder()
                .className(className)
                .methodName(methodName)
                .exception(ex.getClass().getName())
                .message(ex.getMessage())
                .build();
    }
}
