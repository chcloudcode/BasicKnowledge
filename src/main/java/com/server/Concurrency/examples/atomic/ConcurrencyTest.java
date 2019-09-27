package com.server.Concurrency.examples.atomic;

import com.server.Concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * NotThreadSafe 自定义的注解标识 这个类是线程不安全的  错误实例
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch  countDownLatch = new CountDownLatch(clientTotal);
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
        count++;
    }
}
