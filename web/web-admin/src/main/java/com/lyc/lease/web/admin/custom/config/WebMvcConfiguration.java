package com.lyc.lease.web.admin.custom.config;

import com.lyc.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfiguration
 * @Description TODO SpringMVC配置类
 * @Author LuKey_C
 * @Date 2025/2/1 15:50
 * @Version 1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Autowired
//    StringToItemTypeConverter stringToItemTypeConverter;

    @Autowired
    StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    /**
     * 添加自定义类型转换器
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToItemTypeConverter);
        registry.addConverterFactory(stringToBaseEnumConverterFactory);
    }
}
