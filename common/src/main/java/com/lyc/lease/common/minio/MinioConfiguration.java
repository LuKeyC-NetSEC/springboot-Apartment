package com.lyc.lease.common.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MinioConfiguration
 * @Description TODO Minio配置类
 * @Author LuKey_C
 * @Date 2025/2/2 16:06
 * @Version 1.0
 */

@Configuration
@EnableConfigurationProperties(MinioProperties.class) //注册属性类
//@ConfigurationPropertiesScan("com.lyc.lease.common") //注册属性类
@ConditionalOnProperty(name = "minio.endpoint") //当`minio.endpoint`属性存在，该配置类才会生效
public class MinioConfiguration {

    @Autowired
    MinioProperties properties;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
        return minioClient;
    }
}
