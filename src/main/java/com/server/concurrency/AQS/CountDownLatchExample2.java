package com.server.concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private final  static int threadCount =200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch count = new CountDownLatch(threadCount);

        for(int i = 0; i< threadCount ; i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception:",e);
                }finally {
                    count.countDown();
                }
            });
        }
        count.await(10, TimeUnit.MILLISECONDS);//支持给定时间的等待，超过这个时间就不再等待
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
            Thread.sleep(100);
            log.info("{}",threadNum);
    }
}
