package com.server.Concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future 的基本使用
 */

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something......");
            Thread.sleep(6000);
            return "Have done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new MyCallable());
        log.info("do something in main .....");
        Thread.sleep(3000);
        String result = future.get();
        log.info("result : {}",result);
    }
}
