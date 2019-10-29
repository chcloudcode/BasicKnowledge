package com.server.DesignPattern.SingletonPattern;

/**
 * 懒汉，线程不安全 的 单例模式
 * 懒加载：需要的时候再实例化
 * 关键点：
 *  1. 私有化构造方法
 *  2. 只开放一个获取实例的 静态方法
 *  3. 将实例静态化，一个类只有一个实例
 *
 *  线程不安全原因：当多个线程访问 getInstance时，可能造成 线程间 返回的实例不同
 */
public class LazyModeSingleton {

    private static LazyModeSingleton lazyModeSingleton;

    private LazyModeSingleton(){

    }

    public static  LazyModeSingleton getInstance(){
        if(null == lazyModeSingleton){
            return new LazyModeSingleton();
        }
        return lazyModeSingleton;
    }
}
