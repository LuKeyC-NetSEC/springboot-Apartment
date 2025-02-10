package com.lyc.lease.web.app.service.impl;
import com.lyc.lease.common.utils.JwtUtil;
import com.lyc.lease.model.enums.BaseStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyc.lease.common.constant.RedisConstant;
import com.lyc.lease.common.exception.LeaseException;
import com.lyc.lease.common.result.ResultCodeEnum;
import com.lyc.lease.common.utils.CodeUtil;
import com.lyc.lease.model.entity.UserInfo;
import com.lyc.lease.web.app.mapper.UserInfoMapper;
import com.lyc.lease.web.app.service.LoginService;
import com.lyc.lease.web.app.service.SmsService;
import com.lyc.lease.web.app.vo.user.LoginVo;
import com.lyc.lease.web.app.vo.user.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    SmsService smsService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 获取手机验证码
     *
     * @param phone 用户手机号
     * @throws LeaseException 如果发送验证码过于频繁，则抛出LeaseException异常
     */
    @Override
    public void getCode(String phone) {
        String code = CodeUtil.getRandomCode(6);
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;

        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC){
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }

        smsService.sendCode(phone,code);
        redisTemplate.opsForValue().set(key,code,RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
    }

    /**
     * 用户登录
     *
     * @param loginVo 登录信息对象，包含验证码和手机号
     * @return 登录成功后返回的JWT令牌
     * @throws LeaseException 抛出各种登录异常情况，如验证码为空、手机号为空、验证码错误、账号被禁用等
     */
    @Override
    public String login(LoginVo loginVo) {

        if (loginVo.getCode()==null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        if (loginVo.getPhone()==null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        String key = RedisConstant.APP_LOGIN_PREFIX+loginVo.getPhone();
        String code = redisTemplate.opsForValue().get(key);
        if (code == null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }
        if (!code.equals(loginVo.getCode())){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone,loginVo.getPhone());

        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null){
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setNickname("用户-"+loginVo.getPhone().substring(7));
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfoMapper.insert(userInfo);
        }else {
            if (userInfo.getStatus() == BaseStatus.DISABLE){
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }
        }

        return JwtUtil.createToken(userInfo.getId(), userInfo.getPhone());
    }

    /**
     * 根据用户ID获取登录用户信息
     *
     * @param userId 用户ID
     * @return 登录用户信息对象
     */
    @Override
    public UserInfoVo getLogInUserById(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        UserInfoVo userInfoVo = new UserInfoVo(userInfo.getNickname(),userInfo.getAvatarUrl());
        return userInfoVo;
    }
}
