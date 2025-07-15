package com.yaojingxi.serviece;

import com.yaojingxi.pojo.Emp;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.empQueryParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface EmpService {
    //分页查询
    PageResult<Emp> page(empQueryParam param);
    //新建员工
    void save(Emp emp);
    //删除员工（包括员工经历）
    void delete(Integer[] ids);
    //查询回显两个表的内容
    Emp get(Integer id);

    void update(Emp emp);
}
