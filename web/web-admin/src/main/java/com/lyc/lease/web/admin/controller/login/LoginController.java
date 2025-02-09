package com.lyc.lease.web.admin.controller.login;


import com.lyc.lease.common.result.Result;
import com.lyc.lease.common.utils.JwtUtil;
import com.lyc.lease.web.admin.service.LoginService;
import com.lyc.lease.web.admin.vo.login.CaptchaVo;
import com.lyc.lease.web.admin.vo.login.LoginVo;
import com.lyc.lease.web.admin.vo.system.user.SystemUserInfoVo;
import io.jsonwebtoken.Claims;
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

    /**
     * 登录接口
     *
     * @param loginVo 登录信息对象
     * @return 登录结果，包含JWT令牌
     */
    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String jwt = loginService.login(loginVo);
        return Result.ok(jwt);
    }

    /**
     * 获取登陆用户个人信息
     * <p>
     * 此接口用于获取当前登陆用户的个人信息。
     *
     * @param token 用户认证token，从请求头中获取
     * @return 包含用户个人信息的SystemUserInfoVo对象封装在Result对象中
     */
    @Operation(summary = "获取登陆用户个人信息")
    @GetMapping("info")
    public Result<SystemUserInfoVo> info(@RequestHeader("access-token") String token) {
        Claims claims = JwtUtil.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        SystemUserInfoVo result = loginService.getLoginUserInfo(userId);
        return Result.ok(result);
    }
}