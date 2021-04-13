package com.wzs.scheduledtaskdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //允许并行执行
@EnableScheduling
@SpringBootApplication
public class ScheduledTaskDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskDemoApplication.class, args);
    }

}
