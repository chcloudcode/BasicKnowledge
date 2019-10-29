package com.server.DesignPattern.ObserverPattern.JDKObserverPattern;

import java.util.Observable;

public class HanFeiZi extends Observable {

    public void haveBreakfast(){
        String context ="我：韩非子，要吃饭了！！！";
        System.out.println(context);
        super.setChanged();
        super.notifyObservers(context);
    }

    public void haveFun(){
        String context = "我：韩非子，要happy了！！！！";
        System.out.println(context);
        super.setChanged();
        super.notifyObservers(context);
    }

}
