package com.server.Concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * CyclicBarrier 设置等待时间，可能会抛出异常，需要捕获异常才能继续往下执行
 */
@Slf4j
public class CyclicBarrierExample2 {

    //总共有多少个线程同步等待
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        for( int i = 0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                   log.error("exception",e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        //达到某个条件就进入等待状态
        try{
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (Exception e ){
            log.warn("exception",e);
        }

        //当达到设定的同步等待的个数时，就被唤醒继续执行。 
        log.info("{} continue",threadNum);
    }
}
