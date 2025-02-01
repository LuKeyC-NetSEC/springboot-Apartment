package com.lyc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.model.entity.AttrKey;
import com.lyc.lease.model.entity.AttrValue;
import com.lyc.lease.web.admin.service.AttrKeyService;
import com.lyc.lease.web.admin.service.AttrValueService;
import com.lyc.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {

    @Autowired
    AttrValueService attrValueService;

    @Autowired
    AttrKeyService attrKeyService;

    /**
     * 新增或更新属性名称
     *
     * @param attrKey
     * @return Result
     */
    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    /**
     *  新增或更新属性值
     *
     * @param attrValue
     * @return Result
     */
    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }

    /**
     * 查询全部属性名称和属性值列表
     *
     * @return Result<List<AttrKeyVo>>
     */
    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        List<AttrKeyVo> list = attrKeyService.listAttrInfo();
        return Result.ok(list);
    }

    /**
     * 根据id删除属性名称
     *
     * @param attrKeyId
     * @return Result
     */
    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        //删除属性名称
        attrKeyService.removeById(attrKeyId);
        //删除属性名称对应的属性值
        LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttrValue::getAttrKeyId,attrKeyId);
        attrValueService.remove(queryWrapper);
        return Result.ok();
    }

    /**
     * 根据id删除属性值
     *
     * @param id
     * @return Result
     */
    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        attrValueService.removeById(id);
        return Result.ok();
    }

}
