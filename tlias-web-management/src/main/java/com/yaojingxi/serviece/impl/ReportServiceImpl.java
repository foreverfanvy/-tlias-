package com.yaojingxi.serviece.impl;

import com.yaojingxi.mapper.EmpMapper;
import com.yaojingxi.pojo.JobOption;
import com.yaojingxi.serviece.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;


    public JobOption getEmpJobData() {
        //1.调用Mapper接口
        List<Map<String, Object>> list = empMapper.getAll();
        //2.组装职位和数据列表
        //2.1 职位列表的获取
        List<Object> pos = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        //2.2  对应数据获取
        List<Object> num = list.stream().map(dataMap -> dataMap.get("num")).toList();
        //返回一个类
        return new JobOption(pos, num);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.getSex();
    }
}
