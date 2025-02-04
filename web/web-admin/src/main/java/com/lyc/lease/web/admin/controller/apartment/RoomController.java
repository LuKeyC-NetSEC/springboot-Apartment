package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.RoomInfo;
import com.lyc.lease.model.enums.ReleaseStatus;
import com.lyc.lease.web.admin.service.RoomInfoService;
import com.lyc.lease.web.admin.vo.room.RoomDetailVo;
import com.lyc.lease.web.admin.vo.room.RoomItemVo;
import com.lyc.lease.web.admin.vo.room.RoomQueryVo;
import com.lyc.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    RoomInfoService roomInfoService;

    /**
     * 保存或更新房间信息
     *
     * @param roomSubmitVo 房间信息对象
     * @return 操作结果
     */
    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        roomInfoService.saveOrUpdateRoom(roomSubmitVo);
        return Result.ok();
    }

    /**
     * 根据条件分页查询房间列表
     *
     * @param current 当前页码
     * @param size    每页显示的条数
     * @param queryVo 查询条件对象
     * @return 包含房间列表的Result对象
     */
    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        IPage<RoomItemVo> page = new Page<>(current, size);
        IPage<RoomItemVo> result = roomInfoService.pageRoomItemByQuery(page, queryVo);
        return Result.ok(result);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        return Result.ok();
    }

}


















