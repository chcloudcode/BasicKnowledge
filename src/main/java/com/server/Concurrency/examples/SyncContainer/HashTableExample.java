package com.server.Concurrency.examples.SyncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 此时的对HashTable的操作 是线程安全的
 */
@Slf4j
public class HashTableExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static Map<Integer,Integer> hashtable = new Hashtable<>();

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
        log.info("size:{}", hashtable.size());
    }

    private static void update(int threadNo) {
        hashtable.put(threadNo,threadNo);
    }
}
