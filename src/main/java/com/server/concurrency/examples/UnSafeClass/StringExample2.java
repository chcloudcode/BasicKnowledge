package com.server.concurrency.examples.UnSafeClass;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * StringBuffer 线程安全类    方法前面添加了 synchronized 关键字
 * 如果不涉及到线程安全的场景 不建议使用 StringBuffer 否则 会损耗性能
 */
@Slf4j
public class StringExample2 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

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
        log.info("size:{}",stringBuffer.length());

    }

    private static void update() {
        stringBuffer.append("1");
    }
}
