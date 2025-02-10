package com.lyc.lease.web.app.controller.payment;


import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.PaymentType;
import com.lyc.lease.web.app.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "支付方式接口")
@RestController
@RequestMapping("/app/payment")
public class PaymentTypeController {

    @Autowired
    PaymentTypeService paymentTypeService;

    /**
     * 根据房间id获取可选支付方式列表
     *
     * @param id 房间id
     * @return 包含可选支付方式列表的响应结果
     */
    @Operation(summary = "根据房间id获取可选支付方式列表")
    @GetMapping("listByRoomId")
    public Result<List<PaymentType>> list(@RequestParam Long id) {
        List<PaymentType> result = paymentTypeService.listByRoomId(id);
        return Result.ok(result);
    }

    @Operation(summary = "获取全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> list() {
        List<PaymentType> result = paymentTypeService.list();
        return Result.ok(result);
    }
}
