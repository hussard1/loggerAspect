package com.hussard01.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

    @Before("logController()")
    public void beforeController(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        LogHttpRequest logHttpRequest = LogHttpRequest.create(joinPoint, request);

        log.info("*******************************************");
        log.info("HTTP Request ::: {} ", logHttpRequest);
        log.info("*******************************************");
    }

    @AfterReturning(value = "logController()", returning = "ret")
    public void afterController(JoinPoint joinPoint, Object ret) throws Throwable {
        LogHttpResponse logHttpResponse = LogHttpResponse.create(joinPoint, ret);

        log.info("*******************************************");
        log.info("HTTP Response ::: {} ", logHttpResponse);
        log.info("*******************************************");
    }

    @AfterThrowing(pointcut = "logController()", throwing = "ex")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable ex) throws Throwable {

        LogException logException = LogException.create(joinPoint, ex);

        log.error("*******************************************");
        log.error("Exception ::: {} ", logException);
        log.error("*******************************************");
    }



}
