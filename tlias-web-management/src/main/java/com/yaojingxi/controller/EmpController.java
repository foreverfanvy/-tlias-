package com.yaojingxi.controller;

import com.yaojingxi.pojo.Emp;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.Result;
import com.yaojingxi.pojo.empQueryParam;
import com.yaojingxi.serviece.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @Description:员工管理控制器
 * @Author: yaojingxi
 * @Date: 2020/4/5 16:01
 */

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping()
    public Result page(empQueryParam param
//           @RequestParam(defaultValue = "1") Integer page,//当前页码
//           @RequestParam(defaultValue = "10") Integer pageSize,
//           String name , Integer gender, //与前端同名就不用写注解
//           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end//这里要根据接口文档来进行编写格式
    ) {//每一页最大可以显示的数据条数
        log.info("分页查询{}", param);
        //        if(page == null){
        //            page = 10;
        //            log.info("缺少输入，启动默认输入Page=10");
        //        }
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工：{}", Arrays.toString(ids));
        empService.delete(ids);
        return Result.success();
    }
    //进行查询回显和修改数据的联合操作
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("进行查询回显{}号用户",id);
        Emp emp = empService.get(id);
        //返回值封装到emp类
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
