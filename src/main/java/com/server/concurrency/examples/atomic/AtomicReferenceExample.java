package com.server.concurrency.examples.atomic;

import com.server.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);


    public static void main(String[] args) {
        //如果count和期望的值一致，则更新成后者。
        count.compareAndSet(0,1);
        count.compareAndSet(0,2);
        count.compareAndSet(1,4);
        count.compareAndSet(2,5);
        log.info("count:{}",count.get());
    }

}
