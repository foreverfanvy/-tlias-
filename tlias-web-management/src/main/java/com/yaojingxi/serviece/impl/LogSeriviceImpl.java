package com.yaojingxi.serviece.impl;

import com.yaojingxi.mapper.LogMapper;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.log;
import com.yaojingxi.serviece.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogSeriviceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult getByPage(Integer page, Integer pageSize) {
        page = (page-1) * pageSize;
        List<log> result = logMapper.page(page, pageSize);
        return new PageResult(result, logMapper.count());
    }
}
