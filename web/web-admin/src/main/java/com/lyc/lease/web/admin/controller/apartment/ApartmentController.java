package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.ApartmentInfo;
import com.lyc.lease.model.enums.ReleaseStatus;
import com.lyc.lease.web.admin.service.ApartmentInfoService;
import com.lyc.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lyc.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.lyc.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.lyc.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    @Autowired
    ApartmentInfoService apartmentInfoService;
    /**
     * 保存或更新公寓信息
     * <p/>
     * 根据前端传递的公寓提交信息（ApartmentSubmitVo），调用公寓信息服务（ApartmentInfoService）中的保存或更新方法，
     * 更新或新增公寓信息。操作成功后，返回一个成功的Result对象。
     *
     * @param apartmentSubmitVo 公寓提交信息对象，包含公寓的详细信息
     * @return Result 包含操作结果的Result对象，操作成功则返回状态码为200的Result对象
     */
    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentInfoService.saveOrUpdateApartment(apartmentSubmitVo);
        return Result.ok();
    }

    /**
     * 根据条件分页查询公寓列表
     *
     * @param current 当前页码
     * @param size    每页显示的记录数
     * @param queryVo 查询条件对象
     * @return 包含分页信息的公寓列表
     */
    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {
        Page<ApartmentItemVo> page = new Page<>(current,size);
        IPage<ApartmentItemVo> result = apartmentInfoService.pageItem(page,queryVo);
        return Result.ok(result);
    }

    /**
     * 根据ID获取公寓详细信息
     *
     * @param id 公寓ID
     * @return 包含公寓详细信息的Result对象
     */
    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        ApartmentDetailVo result = apartmentInfoService.getDetailById(id);
        return Result.ok(result);
    }

    /**
     * 根据id删除公寓信息
     *
     * @param id 公寓ID
     * @return 删除操作的结果
     */
    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        apartmentInfoService.removeApartmentById(id);
        return Result.ok();
    }

    /**
     * 根据id修改公寓发布状态
     *
     * @param id    公寓的ID
     * @param status 公寓的发布状态
     * @return 返回操作结果
     */
    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        LambdaUpdateWrapper<ApartmentInfo> apartmentInfoUpdateWrapper = new LambdaUpdateWrapper<>();
        apartmentInfoUpdateWrapper.eq(ApartmentInfo::getId,id);
        apartmentInfoUpdateWrapper.set(ApartmentInfo::getIsRelease,status);
        apartmentInfoService.update(apartmentInfoUpdateWrapper);
        return Result.ok();
    }

    /**
     * 根据区县id查询公寓信息列表
     *
     * @param id 区县ID
     * @return 包含公寓信息列表的Result对象
     */
    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        LambdaQueryWrapper<ApartmentInfo> apartmentInfoQueryWrapper = new LambdaQueryWrapper<>();
        apartmentInfoQueryWrapper.eq(ApartmentInfo::getDistrictId,id);
        List<ApartmentInfo> list = apartmentInfoService.list(apartmentInfoQueryWrapper);
        return Result.ok(list);
    }
}