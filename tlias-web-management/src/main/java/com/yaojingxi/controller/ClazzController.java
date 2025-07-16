package com.yaojingxi.controller;

import com.yaojingxi.pojo.Clazz;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.Result;
import com.yaojingxi.pojo.clazzQueryParam;
import com.yaojingxi.serviece.ClazzSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzSerive clazzSerive;

    //3.1查询班级列表——分页条件查询
    @GetMapping()
    public PageResult<Clazz> page(clazzQueryParam param
                                  ) {
        //查询在begin~end之间叫name的内容，返回值是一个PageResult对象，里面存两个值，一个是有限长度的
        return clazzSerive.getPage(param);
    }
    //3.2删除班级
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        clazzSerive.delete(id);
        return Result.success(id);
    }
    //3.3添加班级
    @PostMapping()
    public Result add(@RequestBody Clazz clazz) {
        clazzSerive.insert(clazz);
        return Result.success(clazz);
    }
    //3.6查询所有班级信息
    @GetMapping("/list")
    public  Result list() {
        // 这里可以添加查询所有班级的逻辑
        return Result.success(clazzSerive.list());
    }//@GetMapping("/{id}") 和 @GetMapping("/list") 路径有重叠，导致访问 /clazzs/list 时被当作 id 处理，出现类型转换异常。
    //所有需要更换顺序
    //3.4根据ID查询信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        // 这里可以添加查询逻辑
        Clazz clazz = clazzSerive.getById(id);
        return Result.success(clazz);
    }
    //3.5修改班级信息
    @PutMapping()
    public  Result update(@RequestBody Clazz clazz) {
        //1.查询回显
        clazzSerive.getById(String.valueOf(clazz.getId()));
        //2.进行更新操作
        clazz.setUpdateTime(null); // 假设这里需要设置更新时间
        clazzSerive.update(clazz);
        return  Result.success(clazz);
    }

}
