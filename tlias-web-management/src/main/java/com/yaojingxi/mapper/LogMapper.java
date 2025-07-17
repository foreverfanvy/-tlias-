package com.yaojingxi.mapper;

import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select * from operate_log  order by operate_time desc limit #{page}, #{pageSize}")
    List<log> page(Integer page, Integer pageSize);

    @Select("select count(*) from operate_log")
    Long count();
}
