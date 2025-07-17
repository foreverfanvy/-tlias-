package com.yaojingxi.serviece;

import com.yaojingxi.pojo.PageResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface LogService {

    PageResult getByPage(Integer page, Integer pageSize);
}
