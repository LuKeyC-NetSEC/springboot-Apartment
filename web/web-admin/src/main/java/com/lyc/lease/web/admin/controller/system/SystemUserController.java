package com.lyc.lease.web.admin.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.SystemUser;
import com.lyc.lease.model.enums.BaseStatus;
import com.lyc.lease.web.admin.service.SystemUserService;
import com.lyc.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.lyc.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;


@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {

    @Autowired
    SystemUserService systemUserService;

    /**
     * 根据条件分页查询后台用户列表
     *
     * @param current 当前页码
     * @param size    每页显示数量
     * @param queryVo 查询条件对象
     * @return 包含分页结果和查询条件的Result对象
     */
    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
        IPage<SystemUserItemVo> page = new Page<>(current, size);
        IPage<SystemUserItemVo> result = systemUserService.pageSystemUser(page, queryVo);
        return Result.ok(result);
    }

    /**
     * 根据ID查询后台用户信息
     *
     * @param id 后台用户的唯一标识符
     * @return 包含后台用户信息的Result对象
     */
    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        SystemUserItemVo result = systemUserService.getSystemUserById(id);
        return Result.ok(result);
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
        if (systemUser.getPassword() != null) {
            String enPasswd = DigestUtils.md5Hex(systemUser.getPassword());
            systemUser.setPassword(enPasswd);
        }
        systemUserService.saveOrUpdate(systemUser);
        return Result.ok();
    }

    /**
     * 判断后台用户名是否可用
     *
     * @param username 需要判断的用户名
     * @return 如果用户名可用，则返回包含true的Result对象；否则返回包含false的Result对象
     */
    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemUser::getUsername,username);
        long count = systemUserService.count(queryWrapper);
        return Result.ok(count == 0);
    }

    /**
     * 根据ID删除后台用户信息
     *
     * @param id 后台用户的唯一标识符
     * @return 删除成功的结果对象
     */
    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result removeById(@RequestParam Long id) {
        systemUserService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据ID修改后台用户状态
     *
     * @param id     后台用户的唯一标识符
     * @param status 需要设置的新状态
     * @return 修改成功的结果对象
     */
    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemUser::getId, id);
        updateWrapper.set(SystemUser::getStatus, status);
        systemUserService.update(updateWrapper);
        return Result.ok();
    }
}
