package com.lyc.lease.common.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName LoginUser
 * @Description TODO ThreadLocal的数据类
 * @Author LuKey_C
 * @Date 2025/2/9 16:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class LoginUser {
    private Long userId;
    private String username;
}