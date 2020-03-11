package com.server.Concurrency.AQS;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 常用阻塞队列：
 * 1. ArrayBlockingQueue：数组组成  有界队列   array.length
 * 2. LinkedBlockingQueue：链表组成 有界队列   Integer.MAX_VALUE
 * 3. SynchronousQueue：不存储元素的阻塞队列，单个元素队列
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //失败时会抛异常，为空和 队满的时候
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        System.out.println("---------------------------------------------------");

        //失败时抛特殊值
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println("-----------------------------------------------------");

        //失败时阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
//        blockingQueue.take();

        System.out.println("-------------------------------------------------------");

        //超时
        System.out.println(blockingQueue.offer("a",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d",2L,TimeUnit.SECONDS));

        //
        BlockingQueue<String> syncBlockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println("生产sync阻塞队列");
                syncBlockingQueue.put("1");
                System.out.println("生产sync阻塞队列");
                syncBlockingQueue.put("2");
                System.out.println("生产sync阻塞队列");
                syncBlockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("消费sync阻塞队列："+syncBlockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println("消费sync阻塞队列："+syncBlockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println("消费sync阻塞队列："+syncBlockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BBB").start();


    }
}
