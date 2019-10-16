package com.server.DesignPattern.ProxyPattern.DynamicProxyExample;

public class ClientTest {

    public static void main(String[] args) {
        Human target = new Man();

        Human proxyTarget = (Human) new HumanProxy(target).getHumanProxy();

        proxyTarget.birth();

        Human target2 = new Women();

        proxyTarget = (Human) new HumanProxy(target2).getHumanProxy();

        proxyTarget.birth();

        proxyTarget.color();
    }
}
