package com.server.Concurrency.producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁绑定多个 condition ，控制多个线程顺序执行  精准唤醒
 *
 */

class Resource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void method1() throws Exception{
        lock.lock();
        try{
            while(number!=1){
                //c1 等待
                c1.await();
            }
            System.out.println(Thread.currentThread().getName()+" work!");
            // c1 唤醒  c2
            number=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void method2() throws Exception{
        lock.lock();
        try{
            while(number!=2){
                //c1 等待
                c2.await();
            }
            System.out.println(Thread.currentThread().getName()+" work!");
            // c2 唤醒  c3
            number=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void method3() throws Exception{
        lock.lock();
        try{
            while(number!=3){
                //c3 等待
                c3.await();
            }
            System.out.println(Thread.currentThread().getName()+" work!");
            // c3 唤醒  c1
            number=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ProAndConsumerExample2 {
    public static void main(String[] args) {
        Resource data = new Resource();
        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.method1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();


        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.method2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.method3();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}
