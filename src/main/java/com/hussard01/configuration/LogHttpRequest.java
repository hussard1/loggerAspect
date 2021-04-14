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
public class LogHttpRequest {

    private final String className;
    private final String methodName;
    private final String requestURI;
    private final String requestHeaders;
    private final String requestPayload;

    public static LogHttpRequest create(JoinPoint joinPoint, HttpServletRequest request) {

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        return LogHttpRequest.builder()
                .className(className)
                .methodName(methodName)
                .requestURI(request.getRequestURI())
                .requestHeaders(getRequestHeader(request))
                .requestPayload(getPayload(joinPoint))
                .build();
    }

    private static String getRequestHeader(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getHeaderNames();
        StringBuilder builder = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            String headerName = enumeration.nextElement();
            builder.append(headerName);
            builder.append(":");
            builder.append(request.getHeader(headerName));
        }
        return  builder.toString();
    }

    private static String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(":");
            builder.append(joinPoint.getArgs()[i].toString());
            if (i !=  joinPoint.getArgs().length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
