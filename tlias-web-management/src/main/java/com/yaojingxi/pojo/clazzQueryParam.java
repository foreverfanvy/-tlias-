package com.yaojingxi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class clazzQueryParam {
    private String name;
    private String begin;
    private String end;
    private Integer page = 1;
    private Integer pageSize = 10;
}
