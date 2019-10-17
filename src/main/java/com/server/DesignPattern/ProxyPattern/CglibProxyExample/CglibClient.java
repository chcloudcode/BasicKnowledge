package com.server.DesignPattern.ProxyPattern.CglibProxyExample;

public class CglibClient {

    public static void main(String[] args) {
        TargetObject targetObject = new TargetObject();

        TargetObject proxyTarget = (TargetObject) new ProxyFactory(targetObject).getProxyInstance();

        proxyTarget.hello();
    }
}
