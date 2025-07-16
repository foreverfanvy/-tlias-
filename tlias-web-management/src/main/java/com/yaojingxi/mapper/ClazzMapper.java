package com.yaojingxi.mapper;

import com.yaojingxi.pojo.Clazz;
import com.yaojingxi.pojo.clazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    void deleteById(String id);

    void insert(Clazz clazz);

    Clazz getById(String id);


    void update(Clazz clazz);

    List<Clazz> list();

    List<Clazz> listPage(clazzQueryParam param);
}
