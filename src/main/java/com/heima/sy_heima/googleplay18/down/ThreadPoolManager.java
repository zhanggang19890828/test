package com.heima.sy_heima.googleplay18.down;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者: old样
 * 描述:创建线程池
 * 上海传智播客android黑马程序员
 */

public class ThreadPoolManager {


    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
         int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        // We want at least 2 threads and at most 4 threads in the core pool,
        // preferring to have 1 less than the CPU count to avoid saturating
        // the CPU with background work
        int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
        //int corePoolSize = 3;//核心线程数,正式工
        int maxPoolSize = 10;//最大线程数
        long keepTime = 5;//临时工的时间

        TimeUnit unit = TimeUnit.MINUTES;//时间单位
         LinkedBlockingQueue<Runnable> mWorkQueue;//线程队列

        //new ThreadPoolExecutor.AbortPolicy()这个终止
        //开启线程的工厂Executors.defaultThreadFactory()

        //1. 线程池一创建开启三个线程
        //2. 开启线程池后把后面线程加入队列中
        //3.队列满了,去判断最大的线程数据还有位置
        //4. 如果最大线程也满了
        //5. 判断线程池策略


        mWorkQueue = new LinkedBlockingQueue<>();
        if (mThreadPoolExecutor == null) {
            mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepTime,unit,
                    mWorkQueue, Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        }


    }

    private static ThreadPoolManager sDownManager = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return sDownManager;
    }

    //加入线程到线程池中
    public void addRunnable(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

}
