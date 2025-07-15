package com.yaojingxi.controller;

import com.yaojingxi.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//文件上传和保存到本地磁盘的实现代码，具体配置云环境的方法在这里是没有实现的
@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile  file) throws IOException {
        log.info("上传文件：name={}，age={}，file={}",name,age,file);
        // 保存文件名
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename() +  extension;
        // 转存文件
        file.transferTo(new File("F:/home/yaoyao/TempDate_by_Program/"+filename));
        return Result.success();
    }
}
