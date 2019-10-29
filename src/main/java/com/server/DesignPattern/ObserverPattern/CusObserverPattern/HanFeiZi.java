package com.server.DesignPattern.ObserverPattern.CusObserverPattern;

public class HanFeiZi extends DefaultObservable  {

    public void haveBreakfast(){
        String context ="我：韩非子，要吃饭了！！！";
        System.out.println(context);
        this.notifyObservers(context);
    }

    public void haveFun(){
        String context = "我：韩非子，要happy了！！！！";
        System.out.println(context);
        this.notifyObservers(context);
    }

}
