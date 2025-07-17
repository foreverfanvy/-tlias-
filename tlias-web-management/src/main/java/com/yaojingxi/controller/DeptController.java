package com.yaojingxi.controller;

import com.yaojingxi.anno.Log;
import com.yaojingxi.pojo.Dept;
import com.yaojingxi.pojo.Result;
import com.yaojingxi.serviece.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET) 这个太繁琐了
    @GetMapping("/depts")//路径是可以抽象到类上面的，完整的url=类上的路径+方法上的路径
    public Result list() {
        System.out.println("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping(value = "/depts")
    public Result delete(@RequestParam(value = "id", required = true) Integer id) {
        System.out.println("删除部门数据id=：" + id);
        deptService.delete(id);
        return Result.success();
    }

    //这里需要进行一个JSON格式的数据的传入
    @Log
    @PostMapping(value = "/depts")
    public Result add(@RequestBody Dept dept) {//@RequestBody表示接收JSON格式的数据
        System.out.println("添加部门数据：" + dept);
        deptService.add(dept);
        return Result.success();
    }

    //这里仅仅是根据id查询回显的功能
    @GetMapping(value = "/depts/{id}")//需要使用{}来标识路径参数
    public Result update(@PathVariable("id") Integer id) {//这里注解后面的参数是可以省略的，因为它是和参数一样同名的
        System.out.println("查询部门" + id + "的数据");
        Dept dept = deptService.getByid(id);
        return Result.success(dept);
    }

    //完成修改数据的功能，前提是查询回显功能
    @Log
    @PutMapping( "/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门数据：" + dept);
        deptService.update(dept);
        return Result.success();
    }

}

