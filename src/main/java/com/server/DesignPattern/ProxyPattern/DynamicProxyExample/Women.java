package com.server.DesignPattern.ProxyPattern.DynamicProxyExample;

public class Women implements Human {

    @Override
    public void color() {
        System.out.println("女人比男人白");
    }
    @Override
    public void birth() {
        System.out.println("女人可以生孩子");
    }
}
