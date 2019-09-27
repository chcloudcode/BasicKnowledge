package com.server.Concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *   Semaphore 尝试获取许可
 */
@Slf4j
public class SemaphoreExample3 {


    private final  static int threadCount =50;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        //只允许20个线程并发访问，多余线程会被阻塞
        final Semaphore semaphore = new Semaphore(5);

        for(int i = 0; i< threadCount ; i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
//                   if(semaphore.tryAcquire()){ //尝试获取许可
//                       test(threadNum);
//                       semaphore.release();//释放许可
//                   }

                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){ //尝试获取许可，带超时时间 5s
                        test(threadNum);
                        semaphore.release();//释放许可
                    }
                } catch (Exception e) {
                    log.error("exception:",e);
                }
            });
        }
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
