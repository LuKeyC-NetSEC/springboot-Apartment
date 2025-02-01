package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.FacilityInfo;
import com.lyc.lease.model.enums.ItemType;
import com.lyc.lease.web.admin.service.FacilityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {

    @Autowired
    FacilityInfoService facilityInfoService;

    /**
     * [根据类型]查询配套信息列表
     *
     * @param type
     * @return Result<List<FacilityInfo>>
     */
    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<FacilityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type != null, FacilityInfo::getType, type);
        List<FacilityInfo> facilityInfos = facilityInfoService.list(queryWrapper);
        return Result.ok(facilityInfos);
    }

    /**
     * 新增或修改配套信息
     *
     * @param facilityInfo
     * @return Result
     */
    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        facilityInfoService.saveOrUpdate(facilityInfo);
        return Result.ok();
    }

    /**
     * 根据id删除配套信息
     *
     * @param id
     * @return Result
     */
    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result removeFacilityById(@RequestParam Long id) {
        facilityInfoService.removeById(id);
        return Result.ok();
    }

}
