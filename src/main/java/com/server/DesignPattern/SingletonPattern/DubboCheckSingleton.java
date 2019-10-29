package com.server.DesignPattern.SingletonPattern;

/**
 * 双重校验，线程安全
 *
 * 流程：
 * 1. 线程a判断当前变量instance为空，进入同步代码块，获取类锁再次判断变量为空，执行instance=new Singleton();
 * 2. 此时线程b调用getSingleton()方法，判断变量instance为空，等待线程1释放类锁。
 * 3. 线程a创建对象，将引用赋值给instance。并退出同步代码块，return实例。
 * 4. 线程b,获取类锁。检查变量非空。返回第一次创建的对象
 *
 * 为什么要加 volatile
 * 由于存在指令的重排序问题，当多个线程访问getInstance方法获取实例时，
 * 假如其中一个线程执行了instance=new Singleton(),可能存在instance不为空，
 * 但调用构造方法进行初始化的过程还没有结束，导致返回的实例并没有实例化完成的情况。
 * 此时如果其他线程使用instance，就会报错。
 * volatile的作用就是 取消指令的重排序，使在给instance赋值前，保证构造方法已经执行完成。
 *
 */
public class DubboCheckSingleton {

    private static volatile DubboCheckSingleton instance ;

    private DubboCheckSingleton(){}

    private static DubboCheckSingleton getInstance(){
        if(null == instance){
            synchronized (DubboCheckSingleton.class){
                if(null == instance){
                    instance = new DubboCheckSingleton();
                }
            }
        }
        return instance;
    }

}
