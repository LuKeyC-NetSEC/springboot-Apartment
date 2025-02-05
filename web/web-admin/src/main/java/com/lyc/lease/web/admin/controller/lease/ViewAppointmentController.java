package com.lyc.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.ViewAppointment;
import com.lyc.lease.model.enums.AppointmentStatus;
import com.lyc.lease.web.admin.service.ViewAppointmentService;
import com.lyc.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.lyc.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {

    @Autowired
    ViewAppointmentService viewAppointmentService;

    /**
     * 分页查询预约信息
     *
     * @param current 当前页码
     * @param size    每页显示的记录数
     * @param queryVo 查询条件对象
     * @return 包含分页结果的Result对象
     */
    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        Page<AppointmentVo> page = new Page<>(current, size);
        IPage<AppointmentVo> result = viewAppointmentService.pageAppointment(page,queryVo);
        return Result.ok(result);
    }

    /**
     * 根据id更新预约状态
     *
     * @param id     预约的id
     * @param status 预约状态
     * @return Result对象，表示操作结果
     */
    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ViewAppointment::getId,id);
        updateWrapper.set(ViewAppointment::getAppointmentStatus,status);
        viewAppointmentService.update(updateWrapper);
        return Result.ok();
    }

}
