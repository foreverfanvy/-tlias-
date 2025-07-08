package com.yaojingxi.serviece;

import com.yaojingxi.pojo.Dept;
import com.yaojingxi.pojo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    List<Dept> findAll();//查询所有部门

    void delete(Integer id);

    void add(Dept dept);


    Dept getByid(Integer id);

    void update(Dept dept);
}
