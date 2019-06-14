package com.server.concurrency.examples.SyncContainer;

import com.server.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * 此时 并不是线程安全的 ，这种情况需要在 方法调用端 再加上同步的处理  synchronized
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {

    public static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        while (true) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            });
            thread1.start();
            thread2.start();
        }
    }

}
