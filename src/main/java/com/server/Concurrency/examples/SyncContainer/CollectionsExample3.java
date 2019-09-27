package com.server.Concurrency.examples.SyncContainer;


import com.server.Concurrency.annotation.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程安全方法：Collections.synchronizedMap
 */
@Slf4j
@ThreadSafe
public class CollectionsExample3 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static Map<Integer,Integer> map = Collections.synchronizedMap(Maps.newHashMap());

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
        log.info("size:{}", map.size());
    }

    private static void update(int count) {
        map.put(count,count);
    }
}
