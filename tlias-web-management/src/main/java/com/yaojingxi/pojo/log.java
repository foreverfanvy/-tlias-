package com.yaojingxi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class log {
    private Integer id;
    private String returnValue;
    private LocalDateTime operateTime;
    private String methodName;
    private String methodParams;
    private Integer operateEmpId;
    private Integer costTime;
}
