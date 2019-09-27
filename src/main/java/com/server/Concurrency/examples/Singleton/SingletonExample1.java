package com.server.Concurrency.examples.Singleton;

import com.server.Concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 第一次使用时实例化，线程不安全
 */
@Slf4j
@NotThreadSafe
public class SingletonExample1 {

    private static  SingletonExample1 instance = null;

    private SingletonExample1(){

    }

    public static SingletonExample1 getInstance(){
        if(null==instance){
            instance = new SingletonExample1();
        }
        return instance;
    }


}
