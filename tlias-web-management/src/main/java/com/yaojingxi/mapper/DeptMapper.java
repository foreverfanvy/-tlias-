package com.yaojingxi.mapper;

import com.yaojingxi.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //这里由于mybatis的自动映射问题，有三种解决方法
    @Results({
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_time", property = "createTime")
    })//这一种看起来最舒服，但是还是要写太多东西了，可以再yml里面启动对应的驼峰匹配规则
    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findAll();

    //删除id号的部门数据
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    //添加部门数据
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    //根据id号查询部门数据
    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept getByid(Integer id);

    //修改部门数据
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);

}
