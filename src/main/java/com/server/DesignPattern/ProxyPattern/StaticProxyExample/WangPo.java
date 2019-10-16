package com.server.DesignPattern.ProxyPattern.StaticProxyExample;

public class WangPo implements KindWomen {

    private KindWomen kindWomen;

    public WangPo(KindWomen kindWomen){
        this.kindWomen=kindWomen;
    }

    @Override
    public void happyWithMan() {
        System.out.println("我是代理，我代理别人，干活儿");
        this.kindWomen.happyWithMan();
        System.out.println("我是代理，代理的活儿，干完了");
    }
}
