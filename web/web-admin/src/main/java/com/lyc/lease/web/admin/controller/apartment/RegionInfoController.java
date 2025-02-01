package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.CityInfo;
import com.lyc.lease.model.entity.DistrictInfo;
import com.lyc.lease.model.entity.ProvinceInfo;
import com.lyc.lease.web.admin.service.CityInfoService;
import com.lyc.lease.web.admin.service.DistrictInfoService;
import com.lyc.lease.web.admin.service.ProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "地区信息管理")
@RestController
@RequestMapping("/admin/region")
public class RegionInfoController {

    @Autowired
    ProvinceInfoService provinceInfoService;

    @Autowired
    CityInfoService cityInfoService;

    @Autowired
    DistrictInfoService districtInfoService;

    /**
     * 查询省份信息列表
     *
     * @return Result<List<ProvinceInfo>>
     */
    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        List<ProvinceInfo> provinceInfos = provinceInfoService.list();
        return Result.ok(provinceInfos);
    }

    /**
     * 根据省份id查询城市信息列表
     *
     * @param id
     * @return Result<List<CityInfo>>
     */
    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId,id);
        List<CityInfo> cityInfos = cityInfoService.list(queryWrapper);
        return Result.ok(cityInfos);
    }

    /**
     * 根据城市id查询区县信息
     *
     * @param id
     * @return Result<List<DistrictInfo>>
     */
    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DistrictInfo::getCityId,id);
        List<DistrictInfo> districtInfos = districtInfoService.list(queryWrapper);
        return Result.ok(districtInfos);
    }

}
