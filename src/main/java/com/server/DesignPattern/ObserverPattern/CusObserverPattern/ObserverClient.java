package com.server.DesignPattern.ObserverPattern.CusObserverPattern;

/**
 * 观察者模式  旁观者（吃瓜群众）
 */
public class ObserverClient {

    public static void main(String[] args) {
            //明星人物，韩非子
            HanFeiZi hanFeiZi = new HanFeiZi();

            //偷窥狂，李斯
            LiSi liSi = new LiSi();

            //既然是 明星人物，自然会有很多偷窥狂，这里可以自行添加

            //李斯要监控 韩非子
            hanFeiZi.addObserver(liSi);

            //韩非子有新活动了
            hanFeiZi.haveBreakfast();
            hanFeiZi.haveFun();
    }

}
