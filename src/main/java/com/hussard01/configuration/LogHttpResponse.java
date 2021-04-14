package com.hussard01.configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Getter
@Builder
@ToString
public class LogHttpResponse {

    private final String className;
    private final String methodName;
    private final String responsePayload;

    public static LogHttpResponse create(JoinPoint joinPoint, Object o) {

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        return LogHttpResponse.builder()
                .className(className)
                .methodName(methodName)
                .responsePayload(o.toString())
                .build();
    }
}
