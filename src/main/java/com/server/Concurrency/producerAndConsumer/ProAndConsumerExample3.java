package com.server.Concurrency.producerAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列 来实现生产者消费者，
 *  优点：不自己控制锁的获取和释放，不用管，线程的阻塞和唤醒
 */

class MyResouce{
    //控制是否可以生产 或者 消费
    private volatile Boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResouce(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println("当前阻塞队列类型："+blockingQueue.getClass().getName());
    }

    public void product() throws Exception{
        String data = null;
        Boolean result;
        while(flag){
            data = atomicInteger.incrementAndGet()+"";
            result = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(result){
                System.out.println("插入数据成功");
            }else{
                System.out.println("插入数据失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("停止生产");
    }

    public void consume() throws Exception{

        String result;
        while(flag){
            result = blockingQueue.poll(4L, TimeUnit.SECONDS);
            if(null == result|| result.equalsIgnoreCase("")){
                flag=false;
                System.out.println(Thread.currentThread().getName()+" 超过4s 没有消费到数据 ，消费退出");
            }else{
                System.out.println(Thread.currentThread().getName()+"消费队列"+result+"成功！");
            }
        }
        System.out.println("停止消费");
    }

    public void stop(){
        this.flag=false;
    }

}
public class ProAndConsumerExample3 {
    public static void main(String[] args) {

        MyResouce myResouce = new MyResouce(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println("生产者启动");
            try {
                myResouce.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println("消费者启动");
            try {
                myResouce.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consume").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResouce.stop();


    }

}
