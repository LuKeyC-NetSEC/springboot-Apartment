package com.lyc.lease.web.app.controller.history;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.lease.common.login.LoginUser;
import com.lyc.lease.common.login.LoginUserHolder;
import com.lyc.lease.common.result.Result;
import com.lyc.lease.web.app.mapper.BrowsingHistoryMapper;
import com.lyc.lease.web.app.service.BrowsingHistoryService;
import com.lyc.lease.web.app.vo.history.HistoryItemVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "浏览历史管理")
@RequestMapping("/app/history")
public class BrowsingHistoryController {

    @Autowired
    BrowsingHistoryService browsingHistoryService;

    /**
     * 获取浏览历史
     *
     * @param current 当前页码
     * @param size    每页显示条数
     * @return 浏览历史分页结果
     */
    @Operation(summary = "获取浏览历史")
    @GetMapping("pageItem")
    private Result<IPage<HistoryItemVo>> page(@RequestParam long current, @RequestParam long size) {
        Page<HistoryItemVo> page = new Page<>(current,size);
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        IPage<HistoryItemVo> result = browsingHistoryService.pageItemByUserId(page,userId);
        return Result.ok(result);
    }
}
