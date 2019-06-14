package com.server.concurrency.examples.atomic;

import com.server.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicReferenceFieldUpdaterExample {


     private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdaterExample> updater =
             AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterExample.class,"count");

     @Getter
     private volatile int count = 100;

    public static void main(String[] args) {
        //原子性更新某个类的变量
        AtomicReferenceFieldUpdaterExample example = new AtomicReferenceFieldUpdaterExample();
        if(updater.compareAndSet(example,100,200)){
            log.info("update success 1 , {}",example.getCount());
        }
        if(updater.compareAndSet(example,100,400)){
            log.info("update success 1 , {}",example.getCount());
        }else{
            log.info("update failed ,{}",example.getCount() );
        }
    }
}
