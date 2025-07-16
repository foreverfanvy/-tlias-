package com.yaojingxi.serviece.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yaojingxi.mapper.EmpExprMapper;
import com.yaojingxi.mapper.EmpMapper;
import com.yaojingxi.pojo.*;
import com.yaojingxi.serviece.EmpLogService;
import com.yaojingxi.serviece.EmpService;
import com.yaojingxi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Integer[] ids) {
        //1.删除员工基本信息根据id
        empMapper.deleteByIds(ids);
        //2.根据员工的emp_id来删除员工的经历信息，批量删除
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp get(Integer id) {
        //查询回显
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据id来更新基本信息——上传时间
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.删除之前的员工经历信息
        empExprMapper.deleteByEmpIds(new Integer[]{emp.getId()});
        //3.添加新的经历信息
        List<EmpExpr> exprList = emp.getExprList();// 获取emp里面封装的信息
        if (!CollectionUtils.isEmpty(exprList)) {
            //将其经历设置到对于emp的ExprList中去
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            //将exprList中的经历导入到对应的地方去
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public LoginInfo login(Emp emp) {
        //实现登录的方法
        // 1.调用Mapper来进行查询
        Emp e = empMapper.selectByusernameAndPassword(emp);
        //2.判断是否有这个员工
        if (e != null) {
            //如果存在就返回登录信息
            LoginInfo loginInfo = new LoginInfo(e.getId(), e.getUsername(), e.getName(), null);
            // 使用 LoginInfo 的 getClaims 方法来获取完整的 claims
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginInfo.getId());
            claims.put("username", loginInfo.getUsername());
            claims.put("name", loginInfo.getName());
            // 生成 JWT
            JwtUtils jwtUtils = new JwtUtils();
            String jwt = jwtUtils.generateJwt(claims);
            loginInfo.setToken(jwt);

            log.info("登录成功，用户信息：{}", loginInfo);
            return loginInfo;
        }
        //3.如果失败就直接返回null
        return null;
    }

}
