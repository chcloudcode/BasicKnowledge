package com.server.DesignPattern.ObserverPattern.JDKObserverPattern;

import java.util.Observable;
import java.util.Observer;

public class XiangShaoLong implements Observer {
    @Override
    public void update(Observable o, Object obj) {
        System.out.println("目标有新活动了！！！");
        reportToBoss(obj.toString());
        System.out.println("汇报完毕！！，继续监控。");
    }

    private void reportToBoss(String context) {
        System.out.println("xiangshaolong ,报告Boss，目标有新动向："+context);
    }
}
