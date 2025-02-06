package com.lyc.lease.web.admin.schedule;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyc.lease.model.entity.LeaseAgreement;
import com.lyc.lease.model.enums.LeaseStatus;
import com.lyc.lease.web.admin.service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName ScheduleTasks
 * @Description TODO 定时任务类
 * @Author LuKey_C
 * @Date 2025/2/6 16:56
 * @Version 1.0
 */
@Component
public class ScheduleTasks {

    @Autowired
    private LeaseAgreementService leaseAgreementService;

    /**
     * 定时任务：检查租约状态
     * <p>
     * 使用cron表达式设置任务执行时间，每天午夜执行一次。
     * 检查所有已签订或正在退租的租约，如果租约结束日期早于当前日期，则更新租约状态。
     * ┌───────────── second (0-59)
     * │ ┌───────────── minute (0 - 59)
     * │ │ ┌───────────── hour (0 - 23)
     * │ │ │ ┌───────────── day of the month (1 - 31)
     * │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     * │ │ │ │ │ ┌───────────── day of the week (0 - 7)
     * │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
     * │ │ │ │ │ │
     * * * * * * *
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus() {
        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
        Date now = new Date();
        updateWrapper.le(LeaseAgreement::getLeaseEndDate, now);
        updateWrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
        updateWrapper.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);
        leaseAgreementService.update(updateWrapper);
    }
}
