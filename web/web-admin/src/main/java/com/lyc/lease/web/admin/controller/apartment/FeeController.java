package com.lyc.lease.web.admin.controller.apartment;


import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.FeeKey;
import com.lyc.lease.model.entity.FeeValue;
import com.lyc.lease.web.admin.service.FeeKeyService;
import com.lyc.lease.web.admin.service.FeeValueService;
import com.lyc.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    @Autowired
    FeeValueService feeValueService;

    @Autowired
    FeeKeyService feeKeyService;

    /**
     * 保存或更新杂费名称
     *
     * @param feeKey
     * @return Result
     */
    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    /**
     * 保存或更新杂费值
     *
     * @param feeValue
     * @return Result
     */
    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        feeValueService.saveOrUpdate(feeValue);
        return Result.ok();
    }

    /**
     * 查询全部杂费名称和杂费值列表
     *
     * @return Result<List<FeeKeyVo>>
     */
    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        List<FeeKeyVo> list = feeKeyService.feeInfoList();
        return Result.ok(list);
    }

    /**
     * 根据id删除杂费名称
     *
     * @param feeKeyId
     * @return Result
     */
    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {
        feeKeyService.removeById(feeKeyId);
        return Result.ok();
    }

    /**
     * 根据id删除杂费值
     *
     * @param id
     * @return Result
     */
    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result deleteFeeValueById(@RequestParam Long id) {
        feeValueService.removeById(id);
        return Result.ok();
    }
}
