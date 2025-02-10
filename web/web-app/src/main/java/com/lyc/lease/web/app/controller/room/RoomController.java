package com.lyc.lease.web.app.controller.room;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.web.app.service.RoomInfoService;
import com.lyc.lease.web.app.vo.room.RoomDetailVo;
import com.lyc.lease.web.app.vo.room.RoomItemVo;
import com.lyc.lease.web.app.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "房间信息")
@RestController
@RequestMapping("/app/room")
public class RoomController {

    @Autowired
    RoomInfoService roomInfoService;

    /**
     * 分页查询房间列表
     *
     * @param current 当前页码
     * @param size    每页显示条数
     * @param queryVo  房间查询条件对象
     * @return 包含分页结果和房间列表的Result对象
     */
    @Operation(summary = "分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        IPage<RoomItemVo> page = new Page<>(current, size);
        IPage<RoomItemVo> result = roomInfoService.pageItem(page,queryVo);
        return Result.ok(result);
    }

    /**
     * 根据ID获取房间的详细信息
     *
     * @param id 房间ID
     * @return 包含房间详细信息的Result对象
     */
    @Operation(summary = "根据id获取房间的详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        RoomDetailVo result = roomInfoService.getDetailById(id);
        return Result.ok(result);
    }

    /**
     * 根据公寓ID分页查询房间列表
     *
     * @param current 当前页码
     * @param size    每页显示条数
     * @param id      公寓ID
     * @return 包含分页结果和房间列表的Result对象
     */
    @Operation(summary = "根据公寓id分页查询房间列表")
    @GetMapping("pageItemByApartmentId")
    public Result<IPage<RoomItemVo>> pageItemByApartmentId(@RequestParam long current, @RequestParam long size, @RequestParam Long id) {
        IPage<RoomItemVo> page = new Page<>();
        IPage<RoomItemVo> result = roomInfoService.pageItemByApartmentId(page,id);
        return Result.ok(result);
    }
}
