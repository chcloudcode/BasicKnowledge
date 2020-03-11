package com.server.Concurrency.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * 实现一个自旋锁
 * 自旋锁好处：循环获取锁知道成功为止，没有阻塞
 *
 * 方法：通过cas的思想实现
 * A线程调用myLock获取锁 5s
 * B线程进来发先有线程占用锁，只能自旋等待 A释放锁
 */
public class SpinLockExample {

    AtomicReference<Thread> atomicReference =  new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("当前线程:"+thread.getName());
        while (!atomicReference.compareAndSet(null,thread)){

        }
        System.out.println("当前线程:"+thread.getName()+"获取锁");
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println("当前线程:"+thread.getName()+"释放锁");
    }

    public static void main(String[] args) {

        SpinLockExample demo = new SpinLockExample();
        new Thread(()->{
            try {
                demo.myLock();
                TimeUnit.SECONDS.sleep(5);
                demo.myUnlock();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                demo.myLock();
                TimeUnit.SECONDS.sleep(1);
                demo.myUnlock();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();


    }
}
