package com.lyc.lease.web.admin.service.impl;

import com.lyc.lease.web.admin.service.LoginService;
import com.lyc.lease.web.admin.vo.login.CaptchaVo;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);

        String code = specCaptcha.text().toLowerCase();
        String key = "admin:login:" + UUID.randomUUID();

        redisTemplate.opsForValue().set(key,code,60, TimeUnit.SECONDS);
        return new CaptchaVo(specCaptcha.toBase64(),key);
    }
}
