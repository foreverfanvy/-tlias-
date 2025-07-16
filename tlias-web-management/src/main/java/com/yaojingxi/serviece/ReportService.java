package com.yaojingxi.serviece;

import com.yaojingxi.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();
}
