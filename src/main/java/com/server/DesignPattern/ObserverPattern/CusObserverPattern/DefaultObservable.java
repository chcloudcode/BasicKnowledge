package com.server.DesignPattern.ObserverPattern.CusObserverPattern;

import java.util.ArrayList;

/**
 * 默认 被 观察者 都有的特性
 */
public abstract class DefaultObservable implements Observable{

    //特性1：都有很多 观察者 关注它
    //定义个变长数组，存放所有的观察者
    private ArrayList<Observer> observerList = new ArrayList<Observer>();

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String context) {
            observerList.stream().forEach(each->{
                //通知所有的 观察者
                each.action(context);
            });
    }
}
