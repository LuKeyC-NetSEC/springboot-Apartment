package com.lyc.lease.web.admin.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName ScheduleTasksTest
 * @Description TODO
 * @Author LuKey_C
 * @Date 2025/2/6 19:09
 * @Version 1.0
 */
@SpringBootTest
class ScheduleTasksTest {

    @Autowired
    ScheduleTasks scheduleTasks;

    @Test
    public void test(){
        scheduleTasks.checkLeaseStatus();
    }
}