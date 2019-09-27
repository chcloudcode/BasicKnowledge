package com.server.Concurrency.examples.Singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 注意 ：静态代码块 和 静态变量的执行顺序 和 定义顺序保持一致。如果 调换 ① 和 ②的顺序代码执行结果会发生错误。
 */
@Slf4j
public class SingletonExample4 {

    private SingletonExample4(){

    }

    //①
    private static SingletonExample4 instance = null;

    //②
    static{
        instance = new SingletonExample4();
    }

   public static SingletonExample4 getInstance(){
        return instance;
   }

    public static void main(String[] args) {
        log.info("{}",SingletonExample4.getInstance().hashCode());
    }
}
