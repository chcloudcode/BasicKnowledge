package com.server.concurrency.Lock;

import com.server.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 基本使用案例
 */

@Slf4j
@ThreadSafe
public class StampLockExample2 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static int count = 0;

    private final static StampedLock lock =  new StampedLock();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i<clientTotal;i++){
            pool.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        pool.shutdown();
        log.info("count:{}",count);

    }

    private static void add() {
        long stamp = lock.writeLock();
        try{
            count++;
        }catch (Exception e){
            log.error("exception",e);
        }finally {
            lock.unlock(stamp);
        }
    }


  }
