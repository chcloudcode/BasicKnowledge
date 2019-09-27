package com.server.DesignPattern.StrategyPattern.example01;

public class zhaoyun {

    public static void main(String[] args) {
        //赵云使用第一个锦囊
        Context  context1 = new Context(new FirstSolution());
        context1.operate();

        //赵云使用第二个锦囊
        Context  context2 = new Context(new SecondSolution());
        context2.operate();

        //赵云使用第三个锦囊
        Context  context3 = new Context(new ThirdSolution());
        context3.operate();

    }
}
