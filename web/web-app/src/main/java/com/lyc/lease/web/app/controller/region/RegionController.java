package com.lyc.lease.web.app.controller.region;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.CityInfo;
import com.lyc.lease.model.entity.DistrictInfo;
import com.lyc.lease.model.entity.ProvinceInfo;
import com.lyc.lease.web.app.service.CityInfoService;
import com.lyc.lease.web.app.service.DistrictInfoService;
import com.lyc.lease.web.app.service.ProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "地区信息")
@RestController
@RequestMapping("/app/region")
public class RegionController {

    @Autowired
    private ProvinceInfoService provinceInfoService;

    @Autowired
    private CityInfoService cityInfoService;

    @Autowired
    private DistrictInfoService districtInfoService;

    /**
     * 查询省份信息列表
     *
     * @return 包含省份信息列表的Result对象
     */
    @Operation(summary="查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince(){
        List<ProvinceInfo> list = provinceInfoService.list();
        return Result.ok(list);
    }

    /**
     * 根据省份ID查询城市信息列表
     *
     * @param id 省份ID
     * @return 包含城市信息列表的Result对象
     */
    @Operation(summary="根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id){
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId,id);
        List<CityInfo> list = cityInfoService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 根据城市ID查询区县信息列表
     *
     * @param id 城市ID
     * @return 包含区县信息列表的Result对象
     */
    @GetMapping("district/listByCityId")
    @Operation(summary="根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id){
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DistrictInfo::getCityId,id);
        List<DistrictInfo> list = districtInfoService.list(queryWrapper);
        return Result.ok(list);
    }
}
