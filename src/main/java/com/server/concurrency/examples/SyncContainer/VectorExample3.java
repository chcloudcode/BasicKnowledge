package com.server.concurrency.examples.SyncContainer;

import com.server.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * 此时 并不是线程安全的 ，这种情况需要在 方法调用端 再加上同步的处理  synchronized
 */
@Slf4j
@NotThreadSafe
public class VectorExample3 {

    //Exception in thread "main" java.util.ConcurrentModificationException
   public static void test1(Vector<Integer> vector){
       for (Integer i: vector) {
           if(i.equals(3)){
               vector.remove(i);
           }
       }
   }

   //Exception in thread "main" java.util.ConcurrentModificationException
    public static void test2(Vector<Integer> vector){
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                vector.remove(i);
            }
        }
    }

    public static void test3(Vector<Integer> vector){
        for (int i = 0; i <vector.size() ; i++) {
            if(vector.get(i).equals(3)){
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }


}
