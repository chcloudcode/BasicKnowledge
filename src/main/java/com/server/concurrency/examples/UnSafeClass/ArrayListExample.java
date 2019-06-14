package com.server.concurrency.examples.UnSafeClass;

import com.server.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * ArrayList 线程不安全类，使用时不要声名成静态变量
 */

@Slf4j
@NotThreadSafe
public class ArrayListExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static ArrayList<Integer> arrayList = new ArrayList<>();

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
