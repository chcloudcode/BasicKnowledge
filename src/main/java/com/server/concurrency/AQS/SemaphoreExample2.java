package com.server.concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *   Semaphore 当一次要获取多个许可的情况
 */
@Slf4j
public class SemaphoreExample2 {


    private final  static int threadCount =50;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        //只允许20个线程并发访问，多余线程会被阻塞
        final Semaphore semaphore = new Semaphore(5);

        for(int i = 0; i< threadCount ; i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    semaphore.acquire(5);//获取多个许可，此时 跟单线程一样
                    test(threadNum);
                    semaphore.release(5);//释放许可
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
