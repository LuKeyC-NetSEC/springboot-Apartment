package com.lyc.lease.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO JwtToken工具类
 * @Author LuKey_C
 * @Date 2025/2/9 11:20
 * @Version 1.0
 */

public class JwtUtil {

    private static SecretKey secretKey = Keys.hmacShaKeyFor("6xjQuKjNR38cBEGfnzZJJDmxmjCJuwpM".getBytes());

    /**
     * 生成令牌
     *
     * @param userId 用户ID
     * @param userName 用户名
     * @return 生成的令牌字符串
     */
    public static String createToken(Long userId,String userName){
        return Jwts.builder().
                setExpiration(new Date(System.currentTimeMillis() + 3600000)).
                setSubject("USER_INFO").
                claim("userId", userId).
                claim("userName", userName).
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();
    }
}
