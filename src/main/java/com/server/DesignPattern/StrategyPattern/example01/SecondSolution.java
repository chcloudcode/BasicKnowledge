package com.server.DesignPattern.StrategyPattern.example01;

/**
 * 三个具体的锦囊，如何解决问题
 */
public class SecondSolution implements Solution {
    @Override
    public void solve() {
        System.out.println("第二个锦囊：如刘备贪念美色不思离开，就对他谎称曹操要报赤壁之战的仇，大军压境，想办法把刘备骗回来");
    }
}
