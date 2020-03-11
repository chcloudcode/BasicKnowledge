package com.server.Concurrency.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁、非公平锁、可重入锁
 */
public class ReentrantLockExample  implements Runnable{

    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName()+",    执行method1");
        method2();
    }

    public synchronized  void method2(){
        System.out.println(Thread.currentThread().getName()+",   执行method2");
    }

    public static void main(String[] args) {

        //公平锁
        Lock fair = new ReentrantLock(true);

        //非公平锁 默认 线程会尝试占有锁，不按照申请锁的顺序 优点是 高并发情况下比公平锁
        Lock unfair = new ReentrantLock(false);

        ReentrantLockExample demo = new ReentrantLockExample();

        new Thread(()->{
            demo.method1();
        },"t1").start();

        new Thread(()->{
            demo.method1();
        },"t2").start();

        Thread t3 = new Thread(demo,"t3");
        Thread t4 = new Thread(demo,"t4");
        t3.start();
        t4.start();



    }

    Lock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
          method3();
    }

    private void method3() {
        reentrantLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+",    执行method3");
            method4();
        }finally {
            reentrantLock.unlock();
        }
    }

    private void method4() {
        reentrantLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+",    执行method4");
        }finally {
            reentrantLock.unlock();
        }
    }


}
