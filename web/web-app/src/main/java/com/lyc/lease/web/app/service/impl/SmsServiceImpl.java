package com.lyc.lease.web.app.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.lyc.lease.web.app.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private Client client;

    @Override
    public void sendCode(String phone, String code) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        try {
            client.sendSms(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
