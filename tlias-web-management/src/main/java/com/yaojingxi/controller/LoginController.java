package com.yaojingxi.controller;

import com.yaojingxi.pojo.Emp;
import com.yaojingxi.pojo.LoginInfo;
import com.yaojingxi.pojo.Result;
import com.yaojingxi.serviece.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//处理登录请求
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录请求");
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo != null) {
            log.info("登录成功，用户信息：{}", loginInfo);
            return Result.success(loginInfo);
        } else {
            log.warn("登录失败，用户名或密码错误");
            return Result.error("用户名或密码错误");
        }
    }
}
