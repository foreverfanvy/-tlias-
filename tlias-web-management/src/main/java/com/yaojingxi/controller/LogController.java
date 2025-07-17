package com.yaojingxi.controller;

import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.Result;
import com.yaojingxi.serviece.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult byPage = logService.getByPage(page, pageSize);
        return  Result.success(byPage);
    }
}
