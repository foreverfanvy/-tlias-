package com.yaojingxi.mapper;

import com.yaojingxi.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:员工工作经历的Mapper
 * @Author: yaojingxi
 * @Date: 2020/5/13 10:01
 */
@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(Integer[] ids);
}
