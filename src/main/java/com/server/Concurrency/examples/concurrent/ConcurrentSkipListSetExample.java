package com.server.Concurrency.examples.concurrent;

import com.server.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * ConcurrentSkipListSet 线程安全类
 */

@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static Set<Integer> hashSet = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i<clientTotal;i++){
            int count = i;
            pool.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        pool.shutdown();
        log.info("size:{}",hashSet.size());
    }

    private static void update(int threadNo) {
        hashSet.add(threadNo);
    }

}
