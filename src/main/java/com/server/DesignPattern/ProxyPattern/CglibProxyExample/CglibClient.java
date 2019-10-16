package com.server.DesignPattern.ProxyPattern.CglibProxyExample;

import org.springframework.aop.framework.ProxyFactory;

public class CglibClient {

    public static void main(String[] args) {
        System.out.println("Cglib 代理开始");
        TargetObject targetObject = new TargetObject();

//        TargetObject proxyTarget = new ProxyFactory(targetObject).getProxyInstance();
    }
}
