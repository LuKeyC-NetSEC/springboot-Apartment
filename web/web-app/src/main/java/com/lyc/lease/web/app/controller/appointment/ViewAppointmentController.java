package com.lyc.lease.web.app.controller.appointment;


import com.lyc.lease.common.login.LoginUserHolder;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.ViewAppointment;
import com.lyc.lease.web.app.service.ViewAppointmentService;
import com.lyc.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.lyc.lease.web.app.vo.appointment.AppointmentItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "看房预约信息")
@RestController
@RequestMapping("/app/appointment")
public class ViewAppointmentController {
    @Autowired
    private ViewAppointmentService viewAppointmentService;

    /**
     * 保存或更新看房预约
     *
     * @param viewAppointment 看房预约对象
     * @return 操作结果
     */
    @Operation(summary = "保存或更新看房预约")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ViewAppointment viewAppointment) {
        viewAppointment.setUserId(LoginUserHolder.getLoginUser().getUserId());
        viewAppointmentService.saveOrUpdate(viewAppointment);
        return Result.ok();
    }

    /**
     * 查询个人预约看房列表
     *
     * @return 个人预约看房列表
     */
    @Operation(summary = "查询个人预约看房列表")
    @GetMapping("listItem")
    public Result<List<AppointmentItemVo>> listItem() {
        List<AppointmentItemVo> result = viewAppointmentService.listItemByUserId(LoginUserHolder.getLoginUser().getUserId());
        return Result.ok(result);
    }

    /**
     * 根据ID查询预约详情信息
     *
     * @param id 预约详情ID
     * @return 预约详情信息
     */
    @GetMapping("getDetailById")
    @Operation(summary = "根据ID查询预约详情信息")
    public Result<AppointmentDetailVo> getDetailById(Long id) {
        AppointmentDetailVo result = viewAppointmentService.getDetailById(id);
        return Result.ok(result);
    }

}

