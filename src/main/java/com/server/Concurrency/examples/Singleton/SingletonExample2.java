package com.server.Concurrency.examples.Singleton;

import com.server.Concurrency.annotation.ThreadSafe;

/**
 * 恶汉---线程安全
 * 类装载时就实例化了
 * 缺点：当构造方法操作过多时，初始化很耗时，当实例化后，不适用会造成资源的浪费
 */
@ThreadSafe
public class SingletonExample2 {

    private static SingletonExample2 instance = new SingletonExample2();

    private SingletonExample2(){

    }

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
