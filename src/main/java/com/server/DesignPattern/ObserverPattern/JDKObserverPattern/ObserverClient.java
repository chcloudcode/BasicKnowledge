package com.server.DesignPattern.ObserverPattern.JDKObserverPattern;

/**
 * 观察者模式  旁观者（吃瓜群众）
 */
public class ObserverClient {

    public static void main(String[] args) {
            //明星人物，韩非子
            HanFeiZi hanFeiZi = new HanFeiZi();

            //偷窥狂，李斯
            LiSi liSi = new LiSi();
            XiangShaoLong xiangShaoLong = new XiangShaoLong();
            yixiaochuan yixiaochuan = new yixiaochuan();

            //既然是 明星人物，自然会有很多偷窥狂，这里可以自行添加

            //李斯要监控 韩非子
            hanFeiZi.addObserver(liSi);
            hanFeiZi.addObserver(xiangShaoLong);
            hanFeiZi.addObserver(yixiaochuan);

            //韩非子有新活动了
            hanFeiZi.haveBreakfast();
            hanFeiZi.haveFun();

            //注意：JDK自带的观察者模式，在实现时，先后顺序 同自己实现的观察者模式顺序不一样。
    }

}
