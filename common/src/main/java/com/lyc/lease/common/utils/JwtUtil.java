package com.lyc.lease.common.utils;

import com.lyc.lease.common.exception.LeaseException;
import com.lyc.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
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
     * @param userId   用户ID
     * @param userName 用户名
     * @return 生成的令牌字符串
     */
    public static String createToken(Long userId, String userName) {
        return Jwts.builder().
                setExpiration(new Date(System.currentTimeMillis() + 3600000)).
                setSubject("USER_INFO").
                claim("userId", userId).
                claim("userName", userName).
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token 待解析的JWT令牌
     * @throws LeaseException 如果令牌过期或无效，则抛出异常
     */
    public static void parseToken(String token) {
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            jwtParser.parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}
