package com.server.Concurrency.examples.Singleton;

/**
 * 单例模式 懒汉式线程安全
 */
public class SingletonExample3 {

    private  volatile static SingletonExample3 instance = null;

    private SingletonExample3(){

    }

    //线程虽然安全但 当线程 多时造成阻塞 性能损耗较大
//    public static synchronized SingletonExample3 getInstance(){
//        if(null==instance){
//            instance = new SingletonExample3();
//        }
//        return instance;
//    }

    /**
     * 双重校验锁 需要instance 用volatile关键字修饰， 防止发生指令重排序。
     * @return
     */
     public static SingletonExample3 getInstance(){
        if(null==instance){
            synchronized (SingletonExample3.class){
                if(null==instance){
                    instance=new SingletonExample3();
                }
            }
        }
        return instance;
     }
}
