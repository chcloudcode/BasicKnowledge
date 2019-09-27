package com.server.Concurrency.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * FutureTask 的基本使用
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something......");
                Thread.sleep(6000);
                return "Have done";
            }
        });
        new Thread(futureTask).start();
        log.info("do something in main ");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result：{}",result);
    }
}
