package com.server.concurrency.examples.atomic;

import com.server.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ThreadSafe
public class AtomicLongExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static AtomicLong count = new AtomicLong(0);

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
        log.info("count:{}",count.get());

    }

    private static void add() {
        count.incrementAndGet();
    }
}
