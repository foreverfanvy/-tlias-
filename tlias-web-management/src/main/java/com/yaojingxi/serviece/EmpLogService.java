package com.yaojingxi.serviece;

import com.yaojingxi.pojo.EmpLog;
import org.springframework.stereotype.Service;

@Service
public interface EmpLogService {

    public void insertLog(EmpLog empLog);

}
