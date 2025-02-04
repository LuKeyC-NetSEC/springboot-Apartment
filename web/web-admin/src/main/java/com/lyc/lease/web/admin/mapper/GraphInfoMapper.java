package com.lyc.lease.web.admin.mapper;

import com.lyc.lease.model.entity.GraphInfo;
import com.lyc.lease.model.enums.ItemType;
import com.lyc.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.lyc.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectByItemTypeAndId(ItemType itemType, Long id);

}




