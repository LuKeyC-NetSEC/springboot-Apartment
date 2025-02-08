package com.lyc.lease.common.constant;

/**
 * @ClassName RedisConstant
 * @Description TODO Reids常量
 * @Author LuKey_C
 * @Date 2025/2/8 21:06
 * @Version 1.0
 */
public class RedisConstant {
    public static final String ADMIN_LOGIN_PREFIX = "admin:login:";
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;
    public static final String APP_LOGIN_PREFIX = "app:login:";
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;
    public static final String APP_ROOM_PREFIX = "app:room:";
}
