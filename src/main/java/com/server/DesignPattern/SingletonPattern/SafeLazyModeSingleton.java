package com.server.DesignPattern.SingletonPattern;


/**
 * 懒汉，线程安全 的 单例模式
 * 懒加载：需要的时候再实例化
 * 关键点：
 *  1. 私有化构造方法
 *  2. 只开放一个获取实例的 静态方法
 *  3. 将实例静态化，一个类只有一个实例
 *
 *  线程安全原因：同时只能有一个线程进入方法 getInstance方法
 */
public class SafeLazyModeSingleton {

    private static SafeLazyModeSingleton safeLazyModeSingleton;

    private SafeLazyModeSingleton(){

    }

    public synchronized static  SafeLazyModeSingleton getInstance(){
        if(null == safeLazyModeSingleton){
            return new SafeLazyModeSingleton();
        }
        return safeLazyModeSingleton;
    }
}
