package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

// 9번 AOP
@Slf4j
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(org.example.expert.annotation.TrackLog)")
    public void LoggingAnnotation() {}

    @Around("LoggingAnnotation()")
    public Object adviceAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {

        LocalDateTime time = LocalDateTime.now();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        long userId = (long) request.getAttribute("userId");
        StringBuffer url = request.getRequestURL();
        String method = request.getMethod();

        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            log.info("::: 요청한 사용자 ID : {} ::: ", userId);
            log.info("::: API 요청시간 : {} ::: ", time);
            log.info("::: API 요청 URL : {} {} ::: ", method, url);
        }

    }
}
