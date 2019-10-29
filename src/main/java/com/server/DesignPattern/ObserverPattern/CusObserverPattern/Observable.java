package com.server.DesignPattern.ObserverPattern.CusObserverPattern;

/**
 * 被观察者 接口
 */
public interface Observable {

    /**
     * 添加观察者
     * @param observer
     */
    public void addObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    public void removeObserver(Observer observer);

    /**
     * 一发生点事就 被 观察者 监控到 （通知所有观察者）
     * @param context
     */
    public void notifyObservers(String context);


}
