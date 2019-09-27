package com.server.Concurrency.examples.atomic;

import com.server.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ThreadSafe
public class AtomicBooleanExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception{
        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i<clientTotal;i++){
            pool.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        pool.shutdown();
        log.info("atomicBoolean:{}",atomicBoolean.get());
    }

    private static void test() {
        //原子性操作 只会执行一次。
        if(atomicBoolean.compareAndSet(false,true)){
            log.info("execue success");
        }
    }

}
