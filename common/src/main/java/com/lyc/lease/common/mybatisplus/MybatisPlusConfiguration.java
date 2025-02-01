package com.lyc.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfiguration
 * @Description TODO
 * @Author LuKey_C
 * @Date 2025/1/31 10:47
 * @Version 1.0
 */
@Configuration
@MapperScan("com.lyc.lease.web.*.mapper")
public class MybatisPlusConfiguration {

}
