package com.lyc.lease.web.admin.service.impl;

import com.lyc.lease.common.minio.MinioProperties;
import com.lyc.lease.web.admin.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    MinioClient minioClient;

    @Autowired
    MinioProperties properties;

    /**
     * 文件上传
     *
     * @param file 用户上传的文件
     * @return 文件链接
     */
    @Override
    public String upload(MultipartFile file) {
        try {
            String bucketName = properties.getBucketName();

            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
            if (!bucketExists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());

                minioClient.setBucketPolicy(
                        SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(createBucketPolicyConfig(bucketName))
                        .build());
            }

            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) +
                    "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                    .bucket(bucketName)
                    .stream(file.getInputStream(), file.getSize(), -1) //文件输入流，文件大小，分片大小 若文件大小为已知的分片大小设为-1 minio自动调整
                    .object(filename)
                    .contentType(file.getContentType())
                    .build());
            return String.join("/",properties.getEndpoint(),bucketName,filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据指定的存储桶名称创建存储桶策略配置。
     *
     * @param bucketName 存储桶的名称
     * @return 存储桶策略配置的JSON字符串
     */
    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }
}
