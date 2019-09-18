package com.hxh.dn_netframework.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by HXH at 2019/9/18
 * 线程池管理器
 */
public class ThreadPoolManager {
    // 1.把任务添加到请求队列中
    private LinkedBlockingDeque<Runnable> mQueue = new LinkedBlockingDeque<>();

    // 添加任务
    public void execute(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 2.把任务队列中的任务放到线程池中
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        //r超时的线程
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //r超时的线程
                try {
                    mQueue.put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mThreadPoolExecutor = new ThreadPoolExecutor(4, 20,
                15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), handler);
        // 3.让线程自动启动
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Runnable runnable = null;
                    // 从队列中取出请求
                    try {
                        runnable = mQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (runnable != null) {
                        mThreadPoolExecutor.execute(runnable);
                    }
                }
            }
        };
        mThreadPoolExecutor.execute(runnable);
    }

    // 单例
    private static ThreadPoolManager ourInstance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return ourInstance;
    }
}
