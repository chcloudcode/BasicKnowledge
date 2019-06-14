package com.server.concurrency.examples.UnSafeClass;

import com.server.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * HashSet 线程不安全类，使用时不要声名成静态变量
 */

@Slf4j
@NotThreadSafe
public class HashSetExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static Set<Integer> hashSet = new HashSet<>();

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
