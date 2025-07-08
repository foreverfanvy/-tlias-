package com.yaojingxi;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Arrays;

public class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);//传入这个类自己的字节码对象
    @Test
    public void testLog1(){
        logger.info(() -> "开始计算...");

        int sum;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        sum = Arrays.stream(nums).sum();

        logger.info(() -> "计算结果为: " + sum);
        logger.info(() -> "结束计算...");

        logger.trace(()->"trace");
        logger.debug(()->"debug");
        logger.info(()->"info");
        logger.warn(()->"warn");
        logger.error(()->"error");
    }

    @Test
    public void testLog(){
        System.out.println(LocalDateTime.now() + ":开始计算...");

        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }
        
        System.out.println("计算结果为: "+sum);
        System.out.println(LocalDateTime.now() + "结束计算...");
    }

}
