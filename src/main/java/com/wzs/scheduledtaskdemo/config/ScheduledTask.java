package com.wzs.scheduledtaskdemo.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description 定时任务配置类
 * @Author WuZhiSong
 * @Date 2021/4/13 10:40
 * @Version 1.0.0
 */
@Component
public class ScheduledTask {

    /**
     * @Description 创建定时任务1，打印当前线程和时间戳
     * @Date 10:46 2021/4/13
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0/1 * * * * ?")
    @Async("asyncTaskExecutor")
    public void scheduledTask1() throws InterruptedException {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "定时任务：scheduledTask1" + "当前时间：" + System.currentTimeMillis());
        //死循环，造成线程阻塞
        /*while (true) {
            Thread.sleep(5000);
        }*/
    }

    /**
     * @Description 创建定时任务1，打印当前线程和时间戳
     * @Date 10:47 2021/4/13
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void scheduledTask2() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "定时任务：scheduledTask2" + "当前时间：" + System.currentTimeMillis());
    }
}
