package com.lyc.lease.web.app.controller.login;


import com.lyc.lease.common.result.Result;
import com.lyc.lease.web.app.service.LoginService;
import com.lyc.lease.web.app.vo.user.LoginVo;
import com.lyc.lease.web.app.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录管理")
@RestController
@RequestMapping("/app/")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 获取短信验证码
     *
     * @param phone 用户手机号
     * @return 操作结果
     */
    @GetMapping("login/getCode")
    @Operation(summary = "获取短信验证码")
    public Result getCode(@RequestParam String phone) {
        loginService.getCode(phone);
        return Result.ok();
    }

    /**
     * 登录接口
     *
     * @param loginVo 登录信息对象，包含用户名和密码等登录信息
     * @return 登录成功后返回的包含JWT令牌的Result对象
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = loginService.login(loginVo);
        return Result.ok(token);
    }

    @GetMapping("info")
    @Operation(summary = "获取登录用户信息")
    public Result<UserInfoVo> info() {
        return Result.ok();
    }
}

