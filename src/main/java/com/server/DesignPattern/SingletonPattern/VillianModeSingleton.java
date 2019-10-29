package com.server.DesignPattern.SingletonPattern;

/**
 * 恶汉模式，线程安全
 *
 * 1. 系统启动，类初始化时，就实例化一个对象，线程安全
 * 2. 非懒加载，如果该实例一致没用到，会导致资源浪费
 */
public class VillianModeSingleton {

    private static VillianModeSingleton instance = new VillianModeSingleton();

    private VillianModeSingleton(){}

    public static VillianModeSingleton getInstance(){
        return instance;
    }
}
