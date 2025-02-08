package com.lyc.lease.web.admin.controller.login;


import com.lyc.lease.common.result.Result;
import com.lyc.lease.web.admin.service.LoginService;
import com.lyc.lease.web.admin.vo.login.CaptchaVo;
import com.lyc.lease.web.admin.vo.login.LoginVo;
import com.lyc.lease.web.admin.vo.system.user.SystemUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 获取图形验证码
     *
     * @return 包含图形验证码信息的Result对象
     */
    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo result = loginService.getCaptcha();
        return Result.ok(result);
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        return Result.ok();
    }

    @Operation(summary = "获取登陆用户个人信息")
    @GetMapping("info")
    public Result<SystemUserInfoVo> info() {
        return Result.ok();
    }
}