package com.server.Concurrency.threadPool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * newCachedThreadPool  ScheduledThreadPoolExecutor：最大线程数为 Integer.MAX_VALUE ，可能会导致创建大量线程 导致OOM
 * newFixedThreadPool   newSingleThreadExecutor ：阻塞队列为 LinkedBlockingQueue 最大长度为  Integer.MAX_VALUE 容易导致任务堆积 导致OOM
 */
public class CustomThreadPool {

    public static void main(String[] args) {

        System.out.println("当前机器的核心数："+Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(2,7,3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try{
            for(int i = 0;i<13;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t work !");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
