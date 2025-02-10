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
                setExpiration(new Date(System.currentTimeMillis() + 3600000*24*365L)).
                setSubject("USER_INFO").
                claim("userId", userId).
                claim("userName", userName).
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();
    }

    /**
     * 解析JWT令牌
     * <p>
     * 此方法用于解析传入的JWT令牌，并返回令牌中的Claims对象。
     * 如果令牌无效或过期，将抛出异常。
     *
     * @param token 待解析的JWT令牌
     * @return Claims对象，包含令牌中的信息
     * @throws LeaseException 如果令牌为空、过期或无效，则抛出异常
     */
    public static Claims parseToken(String token) {
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static void main(String[] args) {
        System.out.println(createToken(8L, "15515691952"));
    }
}
