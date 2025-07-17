// File: tlias-web-management/src/main/java/com/yaojingxi/aspect/OperationLogAspect.java

package com.yaojingxi.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaojingxi.pojo.OperateLog;
import com.yaojingxi.mapper.OperateLogMapper;
import com.yaojingxi.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(com.yaojingxi.anno.Log)")
    public void operationMethods() {}

    @Around("operationMethods()")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - start;

        OperateLog log = new OperateLog();
        // Replace the fixed user id with actual logic to retrieve the current user.
        log.setOperateEmpId(CurrentHolder.getCurrentId());
        log.setOperateTime(LocalDateTime.now());
        log.setClassName(joinPoint.getSignature().getDeclaringTypeName());
        log.setMethodName(joinPoint.getSignature().getName());
        try {
            String params = objectMapper.writeValueAsString(joinPoint.getArgs());
            log.setMethodParams(params);
        } catch (JsonProcessingException e) {
            log.setMethodParams("Error parsing parameters");
        }
        try {
            String retVal = objectMapper.writeValueAsString(result);
            log.setReturnValue(retVal);
        } catch (JsonProcessingException e) {
            log.setReturnValue("Error parsing return value");
        }
        log.setCostTime(costTime);

        operateLogMapper.insert(log);
        return result;
    }
}