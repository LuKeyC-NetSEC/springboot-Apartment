package com.lyc.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.LeaseAgreement;
import com.lyc.lease.model.enums.LeaseStatus;
import com.lyc.lease.web.admin.service.LeaseAgreementService;
import com.lyc.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.lyc.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {

    @Autowired
    LeaseAgreementService leaseAgreementService;

    /**
     * 保存或修改租约信息
     *
     * @param leaseAgreement 租约信息实体
     * @return 操作结果
     */
    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

    /**
     * 根据条件分页查询租约列表
     *
     * @param current 当前页码
     * @param size    每页数量
     * @param queryVo 查询条件
     * @return 查询结果
     */
    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        IPage<AgreementVo> page = new Page<>(current, size);
        IPage<AgreementVo> result = leaseAgreementService.pageAgreementByQuery(page, queryVo);
        return Result.ok(result);
    }

    /**
     * 根据id查询租约信息
     *
     * @param id 租约ID
     * @return 查询结果
     */
    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        AgreementVo result = leaseAgreementService.getAgreementById(id);
        return Result.ok(result);
    }

    /**
     * 根据id删除租约信息
     *
     * @param id 租约ID
     * @return 操作结果
     */
    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        leaseAgreementService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据id更新租约状态
     *
     * @param id     租约ID
     * @param status 租约状态
     * @return 操作结果
     */
    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(LeaseAgreement::getId,id);
        updateWrapper.set(LeaseAgreement::getStatus,status);
        leaseAgreementService.update(updateWrapper);
        return Result.ok();
    }

}

