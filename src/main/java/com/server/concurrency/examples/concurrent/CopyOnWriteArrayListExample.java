package com.server.concurrency.examples.concurrent;


import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList 线程安全的并发容器
 */
@Slf4j
public class CopyOnWriteArrayListExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static List<Integer> arrayList = new CopyOnWriteArrayList<>();

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
