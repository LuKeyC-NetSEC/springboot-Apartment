package com.lyc.lease.web.admin.custom.converter;

import com.lyc.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringToBaseEnumConverterFactory
 * @Description TODO 自定义类型转换器工厂 实现数字字符串转换为枚举类 String -> BaseEnum子类类型转换
 * @Author LuKey_C
 * @Date 2025/2/1 16:06
 * @Version 1.0
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String code) {
                T[] constants = targetType.getEnumConstants();
                for (T constant : constants) {
                    if (constant.getCode().equals(Integer.valueOf(code))){
                        return constant;
                    }
                }
                throw new IllegalArgumentException("code" + code + "非法");
            }
        };
    }
}
