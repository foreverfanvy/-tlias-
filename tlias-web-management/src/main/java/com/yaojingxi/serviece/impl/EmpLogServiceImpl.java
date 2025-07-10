package com.yaojingxi.serviece.impl;

import com.yaojingxi.mapper.EmpLogMapper;
import com.yaojingxi.pojo.EmpLog;
import com.yaojingxi.serviece.EmpLogService;
import com.yaojingxi.mapper.EmpLogMapper;
import com.yaojingxi.serviece.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)//为了新建一个事务
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
