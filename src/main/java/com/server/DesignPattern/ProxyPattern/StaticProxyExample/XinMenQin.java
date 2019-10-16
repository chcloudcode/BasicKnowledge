package com.server.DesignPattern.ProxyPattern.StaticProxyExample;

public class XinMenQin {

    public static void main(String[] args) {
        System.out.println("find women by wangpo");
        PanJinLian panJinLian = new PanJinLian();
        WangPo wangPo = new WangPo(panJinLian);
        wangPo.happyWithMan();
        System.out.println("over! a good  day!");
    }
}
