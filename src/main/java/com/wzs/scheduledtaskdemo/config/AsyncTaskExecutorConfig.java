package com.wzs.scheduledtaskdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description 自定义线程池配置,需在@Async()注解中指定使用自定义线程池才有效
 * @Author WuZhiSong
 * @Date 2021/4/13 11:19
 * @Version 1.0.0
 */
@Component
public class AsyncTaskExecutorConfig {

    @Bean(value = "asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        //创建线程池对象
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置线程名前缀
        executor.setThreadNamePrefix("scheduledTask-");
        //设置最大线程数
        executor.setMaxPoolSize(10);
        //设置核心线程数
        executor.setCorePoolSize(3);
        //设置等待队列大小
        executor.setQueueCapacity(100);
        /* 设置饱和策略，当线程池已经达到最大线程数量，没有空闲线程时，新任务处理方式有以下几种策略
           Abort策略：默认策略，新任务提交时直接抛出未检查的异常RejectedExecutionException，该异常可由调用者捕获
           CallerRuns策略：为调节机制，既不抛弃任务也不抛出异常，而是将某些任务回退到调用者。不会在线程池的线程中执行新的任务，而是在调用exector的线程中运行新的任务。
           Discard策略：新提交的任务被抛弃。
           DiscardOlds策略：队列的是“队头”的任务，然后尝试提交新的任务。*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有任务调度完成再关闭线程池，保证所有的任务被正确处理
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //线程池关闭时等待其他任务的时间，不能无限等待，确保应用最后能被关闭。而不是无限期阻塞
        executor.setAwaitTerminationSeconds(60);
        //线程池初始化
        executor.initialize();
        return executor;
    }
}
