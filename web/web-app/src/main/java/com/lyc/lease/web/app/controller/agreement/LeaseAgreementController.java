package com.lyc.lease.web.app.controller.agreement;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyc.lease.common.login.LoginUserHolder;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.LeaseAgreement;
import com.lyc.lease.model.enums.LeaseStatus;
import com.lyc.lease.web.app.service.LeaseAgreementService;
import com.lyc.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lyc.lease.web.app.vo.agreement.AgreementItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app/agreement")
@Tag(name = "租约信息")
public class LeaseAgreementController {

    @Autowired
    LeaseAgreementService leaseAgreementService;

    /**
     * 获取个人租约基本信息列表
     *
     * @return 个人租约基本信息列表
     */
    @Operation(summary = "获取个人租约基本信息列表")
    @GetMapping("listItem")
    public Result<List<AgreementItemVo>> listItem() {
        List<AgreementItemVo> result = leaseAgreementService.listItemByPhone(LoginUserHolder.getLoginUser().getUsername());
        return Result.ok(result);
    }

    /**
     * 根据id获取租约详细信息
     *
     * @param id 租约ID
     * @return 租约详细信息对象
     */
    @Operation(summary = "根据id获取租约详细信息")
    @GetMapping("getDetailById")
    public Result<AgreementDetailVo> getDetailById(@RequestParam Long id) {
        AgreementDetailVo agreementDetailVo = leaseAgreementService.getDetailById(id);
        return Result.ok(agreementDetailVo);
    }

    /**
     * 根据id更新租约状态
     *
     * @param id        租约ID
     * @param leaseStatus 租约状态
     * @return 操作结果
     * @description 用于确认租约和提前退租
     */
    @Operation(summary = "根据id更新租约状态", description = "用于确认租约和提前退租")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus leaseStatus) {
        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(LeaseAgreement::getId, id);
        updateWrapper.set(LeaseAgreement::getStatus, leaseStatus);
        leaseAgreementService.update(updateWrapper);
        return Result.ok();
    }

    /**
     * 保存或更新租约
     *
     * @param leaseAgreement 租约对象
     * @return 操作结果
     * @description 用于续约
     */
    @Operation(summary = "保存或更新租约", description = "用于续约")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

}
