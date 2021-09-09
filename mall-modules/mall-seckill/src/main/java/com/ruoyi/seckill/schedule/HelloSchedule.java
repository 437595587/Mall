package com.ruoyi.seckill.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 开启定时任务
 */
@Component
@EnableScheduling
public class HelloSchedule {

    /**
     * 1、Spring中6位组成，不允许第7位的年
     * 2、在周几的位置，1-7 代表周一到周日
     * 3、使用异步加+定时任务让完成任务不阻塞
     */
    // @Async
    // @Scheduled(cron = "* * * ? * 5")
    // public void hello() {
    //     System.out.println("hello...");
    // }
}
