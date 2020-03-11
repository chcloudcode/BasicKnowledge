package com.server.Concurrency.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 的简单使用
 *
 * 读 锁 是 共享锁
 * 写锁 是 独占锁
 */
@Slf4j
public class ReentrantReadWriteLockExample {

    private volatile Map<String, Object> map = new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public void get(String key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在读");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 读取完成："+map.get(key));
        } finally {
            readLock.unlock();
        }
    }

    public void put(String key, Object value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在写入");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result =  map.put(key, value);
            System.out.println(Thread.currentThread().getName()+" 写入完成");
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantReadWriteLockExample demo = new ReentrantReadWriteLockExample();

        for(int i =1 ;i<=5;i++){
            final int j = i;
            new Thread(()->{
                    demo.put(j+"",j+"");
            },String.valueOf(i)).start();
        }

        for(int i =1 ;i<=5;i++){
            final int j = i;
            new Thread(()->{
                demo.get(j+"");
            },String.valueOf(i)).start();
        }


    }

}
