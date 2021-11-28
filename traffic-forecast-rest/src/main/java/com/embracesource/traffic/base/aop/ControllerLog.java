package com.embracesource.traffic.base.aop;

import com.embracesource.traffic.base.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

/**
 * servlet规范通用日志打印方法
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "aop.log.enable", havingValue = "true")
public class ControllerLog {

    @Pointcut("execution(public * com.embracesource.traffic.*.web.*Controller.*(..))")
    public void matchCondition() {

    }

    /**
     * 打印request和response
     */
    @Around("matchCondition()")
    public Object servletMethodProxy(ProceedingJoinPoint pjp) throws Throwable {
        Instant startTime = Instant.now();

        // 前置处理
        Log log = getLog(pjp);
        StringBuilder sb = new StringBuilder();
        sb.append("操作 = ").append(log.getOperation());
        sb.append(", 请求路径 = ").append(log.getUrl());
        sb.append(", 请求方式 = ").append(log.getMethod());
        sb.append(", 请求参数 = ").append(JsonUtils.objectToJson(pjp.getArgs()));
        String className = log.getClassName();
        sb.append(", 全限定名 = ").append(className);
        String methodName = log.getMethodName();
        sb.append(", 方法名称 = ").append(methodName);

        // 方法执行
        Object result;
        Instant endTime;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            endTime = Instant.now();
            sb.append(", 出现异常");
            sb.append(", 异常名称 = ").append(throwable.getClass().getName());
            sb.append(", 耗时 =  ").append(Duration.between(startTime, endTime).toMillis());
            sb.append("ms");
            ControllerLog.log.info("{}", sb.toString());
            throw throwable;
        }
        // 后置处理
        endTime = Instant.now();
        //sb.append(", 响应 = ").append(JSONObject.toJSON(result));
        sb.append(", 耗时 =  ").append(Duration.between(startTime, endTime).toMillis());
        sb.append("ms");
        ControllerLog.log.info("{}", sb.toString());
        return result;
    }


    public static Log getLog(JoinPoint point) {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        Log log = new Log();
        log.setUrl(request.getRequestURI());
        log.setMethod(request.getMethod());
        log.setParameterMap(request.getParameterMap());


        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 全限定名
        String className = methodSignature.getDeclaringType().getName();
        // 方法名
        String methodName = methodSignature.getName();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        ApiOperation trace = method.getAnnotation(ApiOperation.class);
        String operate;
        if (trace != null) {
            operate = trace.value();
        } else {
            operate = methodName;
        }

        log.setClassName(className);
        log.setMethodName(methodName);
        log.setOperation(operate);
        return log;
    }

    @Getter
    @Setter
    public static class Log {

        /**
         * 完成什么功能
         */
        private String operation;

        /**
         * 全限定名
         */
        private String className;

        /**
         * 方法名称
         */
        private String methodName;

        /**
         * 请求url
         */
        private String url;

        /**
         * 请求方式
         */
        private String method;

        /**
         * 请求参数，键值对
         */
        private Map parameterMap;
    }
}
