package com.yaojingxi.serviece.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yaojingxi.mapper.ClazzMapper;
import com.yaojingxi.pojo.Clazz;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.clazzQueryParam;
import com.yaojingxi.serviece.ClazzSerive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ClazzSeriviceImpl implements ClazzSerive {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> getPage(clazzQueryParam param) {
        //查询在begin~end之间叫name的内容，返回值是一个PageResult对象，里面存两个值，一个是有限长度的
        log.info("分页查询班级信息: {}", param);
        PageHelper pageHelper = new PageHelper();
        //开启分页查询
        pageHelper.startPage(param.getPage(), param.getPageSize());

        List<Clazz> clazzList = clazzMapper.listPage(param);
        Page<Clazz> page = (Page<Clazz>) clazzList;

        return new PageResult<>(page.getResult(), page.getTotal());
    }

    @Override
    public void delete(String id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void insert(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(String id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> list() {
        //返回的是一个List列表对应所有班级的信息，每一个班级的信息是会被加载到对应的clazz类中去
        List<Clazz> clazzList = clazzMapper.list();
        if (clazzList == null || clazzList.isEmpty()) {
            log.info("没有查询到班级信息");
            return null;
        }
        return  clazzList;
    }




}
