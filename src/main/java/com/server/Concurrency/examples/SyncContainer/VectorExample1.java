package com.server.Concurrency.examples.SyncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 此时的 对同步容器Vector 的操作是线程安全的，但并不能说所有情况下都会是 线程安全的，例如VectorExample2
 */
@Slf4j
public class VectorExample1 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static List<Integer> vector = new Vector<>();

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
        log.info("size:{}", vector.size());
    }

    private static void update() {
        vector.add(1);
    }
}
