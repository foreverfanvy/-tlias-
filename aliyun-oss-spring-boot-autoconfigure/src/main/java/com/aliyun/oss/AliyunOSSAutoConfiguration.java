package com.aliyun.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AliyunOSSProperties.class)//用这个来导入AliyunOSSProperties properties
@Configuration
public class AliyunOSSAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AliyunOSSOperator aliyunOSSProperties(AliyunOSSProperties properties) {
        return new AliyunOSSOperator(properties);
    }

}
