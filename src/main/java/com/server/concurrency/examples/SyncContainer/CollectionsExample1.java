package com.server.concurrency.examples.SyncContainer;


import com.server.concurrency.annotation.ThreadSafe;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程安全方法：Collections.synchronizedList
 */
@Slf4j
@ThreadSafe
public class CollectionsExample1 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static List<Integer> arrayList = Collections.synchronizedList(Lists.newArrayList());

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i<clientTotal;i++){
            pool.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        pool.shutdown();
        log.info("size:{}",arrayList.size());
    }

    private static void update() {
        arrayList.add(1);
    }
}
