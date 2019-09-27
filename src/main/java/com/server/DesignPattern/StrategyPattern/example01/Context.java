package com.server.DesignPattern.StrategyPattern.example01;

/**
 * 不同的 时机  打开不同的 锦囊
 */
public class Context {

    private Solution solution;

    public Context(Solution solution){
        this.solution = solution;
    }

    public void operate(){
        this.solution.solve();
    }
}
