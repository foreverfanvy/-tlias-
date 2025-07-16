package com.yaojingxi.serviece;

import com.yaojingxi.pojo.Clazz;
import com.yaojingxi.pojo.PageResult;
import com.yaojingxi.pojo.clazzQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClazzSerive {
    void delete(String id);

    void insert(Clazz clazz);

    Clazz getById(String id);

    void update(Clazz clazz);

    List<Clazz> list();

    PageResult<Clazz> getPage(clazzQueryParam param);
}

