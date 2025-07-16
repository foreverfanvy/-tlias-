package com.yaojingxi.exception;

import com.yaojingxi.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//这是全局异常处理类
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //这里接受的是最顶级的异常
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.info("程序出错了" + e.getMessage());
        return Result.error("程序有问题，请联系管理员！！！");
    }
}
