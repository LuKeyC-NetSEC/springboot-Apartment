package com.lyc.lease.web.app.service;

import com.lyc.lease.web.app.vo.user.LoginVo;
import com.lyc.lease.web.app.vo.user.UserInfoVo;

public interface LoginService {
    void getCode(String phone);

    String login(LoginVo loginVo);

    UserInfoVo getLogInUserById(Long userId);
}
