package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.PaymentType;
import com.lyc.lease.web.admin.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    @Autowired
    PaymentTypeService paymentTypeService;

    /**
     * 查询全部支付方式列表
     * @return Result<List<PaymentType>>
     */

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        List<PaymentType> paymentTypes = paymentTypeService.list();
        return Result.ok(paymentTypes);
    }

    /**
     * 保存或更新支付方式
     * @param paymentType
     * @return Result
     */

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        LambdaUpdateWrapper<PaymentType> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(PaymentType::getName,paymentType.getName());
        paymentTypeService.saveOrUpdate(paymentType,updateWrapper);
        return Result.ok();
    }

    /**
     * 根据ID删除支付方式
     * @param id
     * @return Result
     */
    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id) {
        paymentTypeService.removeById(id);
        return Result.ok();
    }

}















