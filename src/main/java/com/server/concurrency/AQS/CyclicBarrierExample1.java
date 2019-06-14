package com.server.concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 基本使用
 */
@Slf4j
public class CyclicBarrierExample1  {

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
        barrier.await();
        //当达到设定的同步等待的个数时，就被唤醒继续执行。
        log.info("{} continue",threadNum);
    }
}
