package com.lyc.lease.common.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName AliyunSMSProperties
 * @Description TODO 阿里云配置类
 * @Author LuKey_C
 * @Date 2025/2/10 10:30
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSMSProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;
}