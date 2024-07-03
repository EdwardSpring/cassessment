package com.casumo.videorental.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public Logger log(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }

    @Pointcut(value = "within(com.casumo.videorental..*)")
    public void appPointcut() {

    }

    @Pointcut(value = "within(com.casumo.videorental.resource..*) || within(com.casumo.videorental.service.impl..*)")
    public void resourceAndServicePointcut() {

    }

    @Before(value = "resourceAndServicePointcut()")
    public void enteringMethod(JoinPoint joinPoint) {
        log(joinPoint).info("Entering {} with args: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());

    }

    @AfterReturning(value = "resourceAndServicePointcut()", returning = "obj")
    public void exitingMethod(JoinPoint joinPoint, Object obj) {
        log(joinPoint).info("Exiting {} with value: {}", joinPoint.getSignature().toShortString(), obj);

    }

    @AfterThrowing(pointcut = "appPointcut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        log(joinPoint).error("Error in {} with message: {}", joinPoint.getSignature().toShortString(), e.getMessage());
    }
}
