package com.yaojingxi.serviece.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yaojingxi.mapper.EmpMapper;
import com.yaojingxi.pojo.Emp;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.empQueryParam;
import com.yaojingxi.serviece.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    public PageResult<Emp> page(empQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());

        List<Emp> list = empMapper.list(param);
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getResult(), p.getTotal());
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

}
