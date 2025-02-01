package com.lyc.lease.web.admin.custom.converter;

import com.lyc.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringToItemTypeConverter
 * @Description TODO 自定义类型转换器 实现数字字符串转换为ItemType
 * @Author LuKey_C
 * @Date 2025/2/1 15:41
 * @Version 1.0
 */
@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String code) {
        ItemType[] itemTypes = ItemType.values();
        for (ItemType itemType : itemTypes) {
            if (itemType.getCode().equals(Integer.valueOf(code))) {
                return itemType;
            }
        }
        throw new IllegalArgumentException("code" + code + "非法");
    }
}
