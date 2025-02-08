package com.lyc.lease.web.admin.controller.system;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.SystemPost;
import com.lyc.lease.model.enums.BaseStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyc.lease.web.admin.service.SystemPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Tag(name = "后台用户岗位管理")
@RequestMapping("/admin/system/post")
public class SystemPostController {

    @Autowired
    SystemPostService systemPostService;

    /**
     * 分页获取岗位信息
     *
     * @param current 当前页码
     * @param size    每页显示数量
     * @return 包含岗位信息分页结果的 Result 对象
     */
    @Operation(summary = "分页获取岗位信息")
    @GetMapping("page")
    private Result<IPage<SystemPost>> page(@RequestParam long current, @RequestParam long size) {
        IPage<SystemPost> page = new Page<>(current,size);
        IPage<SystemPost> result = systemPostService.page(page);
        return Result.ok(result);
    }

    /**
     * 保存或更新岗位信息
     *
     * @param systemPost 岗位信息对象
     * @return 保存或更新成功的结果对象
     */
    @Operation(summary = "保存或更新岗位信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemPost systemPost) {
        systemPostService.saveOrUpdate(systemPost);
        return Result.ok();
    }

    /**
     * 根据id删除岗位
     *
     * @param id 岗位id
     * @return 删除成功的结果对象
     */
    @DeleteMapping("deleteById")
    @Operation(summary = "根据id删除岗位")
    public Result removeById(@RequestParam Long id) {
        systemPostService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据id获取岗位信息
     *
     * @param id 岗位的唯一标识符
     * @return 包含岗位信息的Result对象，如果岗位不存在则返回空
     */
    @GetMapping("getById")
    @Operation(summary = "根据id获取岗位信息")
    public Result<SystemPost> getById(@RequestParam Long id) {
        SystemPost result = systemPostService.getById(id);
        return Result.ok(result);
    }

    /**
     * 获取全部岗位列表
     *
     * @return 包含所有岗位信息的Result对象
     */
    @Operation(summary = "获取全部岗位列表")
    @GetMapping("list")
    public Result<List<SystemPost>> list() {
        List<SystemPost> result = systemPostService.list();
        return Result.ok(result);
    }

    /**
     * 根据岗位id修改状态
     *
     * @param id     岗位的唯一标识符
     * @param status 岗位的状态
     * @return 修改成功的结果对象
     */
    @Operation(summary = "根据岗位id修改状态")
    @PostMapping("updateStatusByPostId")
    public Result updateStatusByPostId(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemPost> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemPost::getId,id);
        updateWrapper.set(SystemPost::getStatus,status);
        systemPostService.update(updateWrapper);
        return Result.ok();
    }
}
