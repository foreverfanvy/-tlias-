package com.yaojingxi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页结果封装类
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T>{
    private List<T> rows;
    private Long total;
}
