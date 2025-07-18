package com.yaojingxi.mapper;

import com.yaojingxi.pojo.Emp;
import com.yaojingxi.pojo.empQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @Description:员工的Mapper
 * @Author: yaojingxi
 * @Date: 2020/5/9 16:05
 */

@Mapper
public interface EmpMapper {
//    ------------------------------------------------------原始代码---------------------------------------------------------------
    //    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //    public Long count();
    //
    //    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc limit #{start},#{pageSize}")
    //    public List<Emp> list(Integer start,Integer pageSize);


    //@Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    public List<Emp> list(empQueryParam param);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username,password,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary}," +
            "#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    //动态SQL在xml中进行定义
    void deleteByIds(Integer[] ids);

    //太长了用xml来写
    Emp getById(Integer id);


    void updateById(Emp emp);

    //统计员工职位人数的
    @MapKey("pos")
    List<Map<String, Object>> getAll();

    @MapKey("name")
    List<Map> getSex();

    Emp selectByusernameAndPassword(Emp emp);
}
