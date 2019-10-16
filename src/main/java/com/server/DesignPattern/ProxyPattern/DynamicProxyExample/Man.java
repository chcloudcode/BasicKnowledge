package com.server.DesignPattern.ProxyPattern.DynamicProxyExample;

public class Man implements Human {
    @Override
    public void color() {
        System.out.println("男人比女人黑");
    }

    @Override
    public void birth() {
        System.out.println("男人不能生孩子");
    }
}
