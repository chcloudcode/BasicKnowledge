package com.server.concurrency.examples.UnSafeClass;

import com.server.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  SimpleDateFormat 类是个线程不安全类，多线程访问时，为了保证安全，每个线程使用时new 一个实例即可（堆栈封闭--局部变量）
 */

@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    private static int clientTotal = 5000;

    private static int threadTotal = 200;

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

    }

    private static void update() {
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.parse("2019-03-11");
        }catch (Exception e){
            log.error("parser error",e);
        }
    }

}
