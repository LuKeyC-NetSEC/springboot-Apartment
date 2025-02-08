package com.lyc.lease.web.admin.service;

import com.lyc.lease.web.admin.vo.login.CaptchaVo;
import com.lyc.lease.web.admin.vo.login.LoginVo;
import com.lyc.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();
}
