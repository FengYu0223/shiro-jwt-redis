package com.mydemo.shriojwt.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @program: shrio-jwt
 * @description:
 * @author: Yu
 * @create: 2020-07-25 09:13
 **/

@Aspect
@Component
@Slf4j
public class WebAspectConfig {

    ThreadLocal<Long> startTime = new ThreadLocal<>();// 开始时间

    @Pointcut("execution(public * com.mydemo.shriojwt.controller.*.*(..))")
    public void webAspect() {
        //切点
    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @Before("webAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        //设置请求进来时的时间戳
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("=======开始执行=========");
        log.info("== URL :{} ", request.getRequestURL().toString());
        log.info("== HTTP_METHOD : {}", request.getMethod());
        log.info("= IP : {}", request.getRemoteAddr());
        log.info("== PATH : {}", request.getServletPath());
        log.info("== METHOD : {}", request.getMethod());
        log.info("== CLASS_METHOD :{}.{} ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("== ARGS :{} ", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj", pointcut = "webAspect()")
    public void doAfterReturning(Object obj) throws Throwable {
        //处理完请求，返回内容
        log.info("=======开始响应=========");
        log.info("== RESPONSE :{} ", JSON.toJSONString(obj));
        log.info("== 响应时间:{} ms", System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }

    @AfterThrowing(value = "webAspect()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        //目标方法名：
        log.info("=======响应异常=========");
        log.info(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException) {
            log.info("发生了空指针异常!!!!!");
        } else {
            log.info("发生了未知异常!!!!!");
        }
        log.info("== 响应时间:{} ms", System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }


}
