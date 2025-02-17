package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.LabelInfo;
import com.lyc.lease.model.enums.ItemType;
import com.lyc.lease.web.admin.service.LabelInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {

    @Autowired
    LabelInfoService labelInfoService;

    /**
     * （根据类型）查询标签列表
     *
     * @param type
     * @return Result<List < LabelInfo>>
     */
    @Operation(summary = "（根据类型）查询标签列表")
    @GetMapping("list")
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {
        System.out.println(type);
        LambdaQueryWrapper<LabelInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type != null, LabelInfo::getType, type);
        List<LabelInfo> labelInfos = labelInfoService.list(queryWrapper);
        return Result.ok(labelInfos);
    }

    /**
     * 新增或修改标签信息
     *
     * @param labelInfo
     * @return Result
     */
    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {
        labelInfoService.saveOrUpdate(labelInfo);
        return Result.ok();
    }

    /**
     * 根据id删除标签信息
     *
     * @param id
     * @return Result
     */
    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result deleteLabelById(@RequestParam Long id) {
        labelInfoService.removeById(id);
        return Result.ok();
    }
}
