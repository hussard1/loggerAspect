package com.hussard01.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Slf4j
@Component
public class LoggerAspect {

    @Pointcut("execution(* com.hussard01.application..*Controller.*(..))")
    public void logController() {}

    @Around("@annotation(LoggerExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info("{} : {} signature execution time is {} ms", className, methodName, executionTime);
        return proceed;
    }

    @Around("logController()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        LogRequest logRequest = LogRequest.create(joinPoint, request);

        log.info("Request is {} ", logRequest);
        return joinPoint.proceed();
    }


}
