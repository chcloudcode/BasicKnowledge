package com.server.concurrency.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 *  Condition 的基本使用
 */
@Slf4j
public class ConditionExample {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();  //加入AQS的等待队列
                log.info("wait signal"); // 1
                condition.await();    //从AQS队列释放 => 释放锁  并 立刻加入到condition的等待队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();  //加入到AQS的 等待队列中
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();  // 发送信号的方法
            log.info("send signal ~ "); // 3  此时 condition 队列中有线程1 节点。 被取出来 又放到了 AQS的等待队列中，但并未被唤醒。
            reentrantLock.unlock();  // 释放锁后 AQS  中只有线程1 ，线程然后依次被唤醒，然后线程1 继续执行。
        }).start();
    }
}
