package com.server.DesignPattern.ObserverPattern.CusObserverPattern;

/**
 * 李斯，韩非子的粉丝，秦老大派他偷窥韩非子
 */
public class LiSi implements Observer {
    @Override
    public void action(String context) {
        System.out.println("目标有新活动了！！！");
        reportToBoss(context);
        System.out.println("汇报完毕！！，继续监控。");
    }

    private void reportToBoss(String context) {
        System.out.println("报告Boss，目标有新动向："+context);
    }


}
