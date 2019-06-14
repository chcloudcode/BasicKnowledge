package com.server.concurrency.examples.Singleton;

import com.server.concurrency.annotation.Recommend;
import com.server.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用枚举类创建单利模式实例  最安全。
 */

@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample5 {

    private SingletonExample5(){

    }

    public static SingletonExample5 getInstance(){
       return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample5 singleton = null;

        //JVM保证这个方法只被执行一次
        Singleton(){
            singleton=new SingletonExample5();
        }

        public SingletonExample5 getSingleton(){
            return  singleton;
        }
    }

}
