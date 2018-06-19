package com.lw.share.commons.thread.pool;/**
 * Created by liuchen on 2018/6/19.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author 刘晨
 * @create 2018-06-19 16:50
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
@Slf4j
public class ThreadPoolConf implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        log.info("--------线程池出初始化开始--------");
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //设置核心线程数
        threadPool.setCorePoolSize(10);
        //设置最大线程数
        threadPool.setMaxPoolSize(10);
        //线程池所使用的缓冲队列
        threadPool.setQueueCapacity(10);
        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(60);
        //  线程名称前缀
        threadPool.setThreadNamePrefix("thread-pool-");
        // 初始化线程
        threadPool.initialize();
        log.info("--------线程池出初始化结束--------");
        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
