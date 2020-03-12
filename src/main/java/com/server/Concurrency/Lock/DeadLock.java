package com.server.Concurrency.Lock;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个简单的死锁类
 * 当DeadLock类的对象flag==1时（td1），先锁定o1,睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2,睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */

@Slf4j
public class DeadLock implements Runnable {

    private String lockA;
    private String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockA+"\t 尝试获得："+lockB);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockB+"\t 尝试获得："+lockA);
                }
            }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLock td1 = new DeadLock(lockA,lockB);
        DeadLock td2 = new DeadLock(lockB,lockA);
        new Thread(td1,"threadA").start();
        new Thread(td2,"threadB").start();
    }
}
