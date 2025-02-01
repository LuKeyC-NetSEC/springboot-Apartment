package com.lyc.lease.web.admin.custom.converter;

import com.lyc.lease.model.enums.ItemType;
import com.lyc.lease.model.enums.LeaseStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringToLeaseStatusConverter
 * @Description TODO 自定义类型转换器 实现数字字符串转换为LeaseStatus
 * @Author LuKey_C
 * @Date 2025/2/1 15:57
 * @Version 1.0
 */
@Component
public class StringToLeaseStatusConverter implements Converter<String, LeaseStatus> {
    @Override
    public LeaseStatus convert(String code) {
        LeaseStatus[] leaseStatuses = LeaseStatus.values();
        for (LeaseStatus leaseStatus : leaseStatuses) {
            if (leaseStatus.getCode().equals(Integer.valueOf(code))) {
                return leaseStatus;
            }
        }
        throw new IllegalArgumentException("code" + code + "非法");
    }
}
