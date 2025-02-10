package com.lyc.lease.web.app.service.impl;

import com.lyc.lease.model.entity.ApartmentInfo;
import com.lyc.lease.model.entity.LabelInfo;
import com.lyc.lease.model.enums.ItemType;
import com.lyc.lease.web.app.mapper.ApartmentInfoMapper;
import com.lyc.lease.web.app.mapper.GraphInfoMapper;
import com.lyc.lease.web.app.mapper.LabelInfoMapper;
import com.lyc.lease.web.app.mapper.RoomInfoMapper;
import com.lyc.lease.web.app.service.ApartmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.lease.web.app.vo.apartment.ApartmentItemVo;
import com.lyc.lease.web.app.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    @Autowired
    ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    LabelInfoMapper labelInfoMapper;

    @Autowired
    GraphInfoMapper graphInfoMapper;

    @Autowired
    RoomInfoMapper roomInfoMapper;

    @Override
    public ApartmentItemVo selectApartmentItemVoById(Long id) {
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);

        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);

        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id);

        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(id);

        ApartmentItemVo apartmentItemVo = new ApartmentItemVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentItemVo);

        apartmentItemVo.setGraphVoList(graphVoList);
        apartmentItemVo.setLabelInfoList(labelInfoList);
        apartmentItemVo.setMinRent(minRent);
        return null;
    }
}




