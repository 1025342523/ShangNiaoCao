package com.yscoco.uppernest.commonlibrary.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程管理器
 */

public class ThreadUtils {

    private static ThreadPool mThreadPool;

    public static ThreadPool getThreadPool() {
        if (mThreadPool == null) {
            synchronized (ThreadUtils.class) {
                if (mThreadPool == null) {
                    int cpuCount = Runtime.getRuntime().availableProcessors();//获取cpu数量
                    int threadCount = cpuCount * 2 + 1;
                    mThreadPool = new ThreadPool(threadCount, threadCount, 1L);
                }
            }
        }
        return mThreadPool;
    }

    public static class ThreadPool {

        private int corePoolSize;//核心线程数
        private int maximunPoolSize;//最大线程数
        private long keepAliveTime;//休息时间

        private ThreadPoolExecutor executor;

        private ThreadPool(int corePoolSize, int maximunPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximunPoolSize = maximunPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public void execute(Runnable r) {
            if (executor == null) {
                executor = new ThreadPoolExecutor(corePoolSize,
                        maximunPoolSize, keepAliveTime, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(),
                        Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
                // 参1:核心线程数;参2:最大线程数;参3:线程休眠时间;参4:时间单位;参5:线程队列;参6:生产线程的工厂;参7:线程异常处理策略
            }
            //线程池执行一个Runnable对象，具体时机线程池说了算
            executor.execute(r);
        }

        /**
         * 取消任务
         */
        public void cancel(Runnable r) {
            if (executor != null) {
                //从线程队列中移除对象
                executor.getQueue().remove(r);
            }
        }
    }
}
