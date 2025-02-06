package com.lyc.lease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName AdminWebApplication
 * @Description TODO 启动类
 * @Author LuKey_C
 * @Date 2025/1/31 10:36
 * @Version 1.0
 */

@SpringBootApplication
@EnableScheduling //开启定时任务支持
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
