package com.lyc.lease.web.admin.mapper;

import com.lyc.lease.model.entity.ApartmentFacility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.lease.model.entity.FacilityInfo;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.lyc.lease.model.ApartmentFacility
*/
public interface ApartmentFacilityMapper extends BaseMapper<ApartmentFacility> {

    List<FacilityInfo> selectListByApartmentId(Long id);
}




