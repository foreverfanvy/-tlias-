package com.yaojingxi.serviece.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yaojingxi.mapper.EmpExprMapper;
import com.yaojingxi.mapper.EmpMapper;
import com.yaojingxi.pojo.*;
import com.yaojingxi.serviece.EmpLogService;
import com.yaojingxi.serviece.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;//因为事务之间的传播是在事务层进行，不是数据层的，所以这里注入的是EmpLogService

    public PageResult<Emp> page(empQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());

        List<Emp> list = empMapper.list(param);
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getResult(), p.getTotal());
    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED) //这个注解表示这个方法要开启事务
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //但是这里加入的数据，里面有empId，所以这里要设置empId
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {//为了保证日志部分必须都进行无论有没有异常
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            //实际上我们不需要id，仅仅只要操作实践和操作信息即可，所有这里传入null（任意值也是可以的）
            empLogService.insertLog(empLog);
        }


    }

}
