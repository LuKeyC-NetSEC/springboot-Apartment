package com.lyc.lease.web.app.service.impl;

import com.lyc.lease.common.constant.RedisConstant;
import com.lyc.lease.common.exception.LeaseException;
import com.lyc.lease.common.result.ResultCodeEnum;
import com.lyc.lease.common.utils.CodeUtil;
import com.lyc.lease.web.app.service.LoginService;
import com.lyc.lease.web.app.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    SmsService smsService;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 获取手机验证码
     *
     * @param phone 用户手机号
     * @throws LeaseException 如果发送验证码过于频繁，则抛出LeaseException异常
     */
    @Override
    public void getCode(String phone) {
        String code = CodeUtil.getRandomCode(6);
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;

        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC){
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }

        smsService.sendCode(phone,code);
        redisTemplate.opsForValue().set(key,code,RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
    }
}
