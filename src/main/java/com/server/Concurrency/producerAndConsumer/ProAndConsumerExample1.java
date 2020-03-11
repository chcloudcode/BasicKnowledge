package com.server.Concurrency.producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者
 */

class resource{
    private int number = 0;
    private Lock  lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try{
            //多线程判断使用while，防止虚假唤醒
            while(number!=0){
                //等待，不能生产
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+" \t" +number );
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try{
            //多线程判断使用while，防止虚假唤醒
            while(number==0){
                //等待，不能消费
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+" \t" +number );
            //通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ProAndConsumerExample1 {

    public static void main(String[] args) {
        resource data = new resource();
        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for(int i = 0;i<5;i++){
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();

    }

}
