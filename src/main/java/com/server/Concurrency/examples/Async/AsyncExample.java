package com.server.Concurrency.examples.Async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized 关键字
 */
@Slf4j
public class AsyncExample {

    //修饰对象，静态代码块
    public  void test1(int objectName){
        synchronized(this){
            for(int i = 0;i<5;i++){
                log.info("test1 execute,object{},{} ",objectName, i);
            }
        }
    }

    //修饰类
    public  void test4(int objectName){
        synchronized(AsyncExample.class){
            for(int i = 0;i<5;i++){
                log.info("test1 execute,object{},{} ",objectName, i);
            }
        }
    }

    //修饰成员方法
    public synchronized void test2(int objectName){
        for(int i = 0;i<5;i++){
            log.info("test2 execute,object{},{}",objectName,i);
        }
    }

    //修饰静态方法
    public synchronized static void test3(int objectName){
        for(int i = 0;i<5;i++){
            log.info("test3 execute,object{},{}",objectName,i);
        }
    }

    public static void main(String[] args) {
        AsyncExample syncExample = new AsyncExample();
        AsyncExample syncExample2 = new AsyncExample();
        ExecutorService pools = Executors.newCachedThreadPool();
        pools.execute(()->{
            syncExample.test4(1);
        });

        pools.execute(()->{
            syncExample2.test4(2);
        });
    }
}
