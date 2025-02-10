package com.lyc.lease.web.app.service.impl;

import com.lyc.lease.web.app.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName SmsServiceImplTest
 * @Description TODO
 * @Author LuKey_C
 * @Date 2025/2/10 10:44
 * @Version 1.0
 */
@SpringBootTest
class SmsServiceImplTest {
    @Autowired
    SmsService smsService;
    @Test
    void sendCode() {
        smsService.sendCode("15515691952","8888");
    }
}